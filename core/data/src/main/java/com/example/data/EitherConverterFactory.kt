package com.example.data

import arrow.core.Either
import arrow.core.Either.Left
import arrow.core.left
import arrow.core.right
import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.converter.Converter
import de.jensklingenberg.ktorfit.converter.KtorfitResult
import de.jensklingenberg.ktorfit.converter.TypeData
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.runBlocking

@Suppress("TooGenericExceptionCaught")
class EitherConverterFactory : Converter.Factory {
    override fun suspendResponseConverter(
        typeData: TypeData,
        ktorfit: Ktorfit,
    ): Converter.SuspendResponseConverter<HttpResponse, Either<*, *>>? {
        if (typeData.typeInfo.type == Either::class) {
            return object : Converter.SuspendResponseConverter<HttpResponse, Either<*, *>> {
                override suspend fun convert(result: KtorfitResult): Either<Any, Any> =
                    result.fold(::Left) {
                        readBody(it, typeData)
                    }
            }
        }
        return null
    }

    /**
     * コンバートする必要があればコンバートしたレスポンスを返す
     * @param typeData: リクエストされたレスポンスタイプに関する情報を持っている。今回の場合、リクエスト関数の戻り値にEitherが指定されていれば、Eitherにコンバートして返す
     */
    override fun responseConverter(
        typeData: TypeData,
        ktorfit: Ktorfit,
    ): Converter.ResponseConverter<HttpResponse, Either<*, *>>? {
        // TODO: https://foso.github.io/Ktorfit/converters/migration/ を参考にrunBlocking使わなくてもいけるかも
        /**
         * Params:
         * getResponse - A suspend function that returns the HttpResponse to be converted.
         * Returns:
         * the converted HttpResponse
         */
        if (typeData.typeInfo.type == Either::class) {
            return object : Converter.ResponseConverter<HttpResponse, Either<*, *>> {
                override fun convert(getResponse: suspend () -> HttpResponse): Either<Any, Any> =
                    runBlocking {
                        try {
                            readBody(getResponse(), typeData)
                        } catch (e: Throwable) {
                            e.left()
                        }
                    }
            }
        }
        return null
    }

    private suspend fun readBody(
        httpResponse: HttpResponse,
        typeData: TypeData,
    ): Either<Any, Any> =
        try {
            httpResponse.body<Any>(typeData.typeArgs[1].typeInfo).right()
        } catch (ex: Exception) {
            ex.left()
        }
}

internal inline fun <T> KtorfitResult.fold(
    onFailure: (Throwable) -> T,
    onSuccess: (HttpResponse) -> T,
): T =
    when (this) {
        is KtorfitResult.Failure -> onFailure(throwable)
        is KtorfitResult.Success -> onSuccess(this.response)
    }
