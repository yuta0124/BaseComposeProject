package com.example.data

import com.example.model.AppError
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.ResponseException
import io.ktor.util.cio.ChannelReadException
import kotlinx.coroutines.TimeoutCancellationException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkService @Inject constructor() {
    @Suppress("TooGenericExceptionCaught")
    suspend inline operator fun <T : Any> invoke(
        block: () -> T,
    ): T = try {
        block()
    } catch (e: Throwable) {
        throw e.toAppError()
    }
}

fun Throwable.toAppError(): AppError = when (this) {
    is AppError -> this
    is ResponseException -> {
        // TODO: ステータスコードにてパースした独自エラーを返す
        // when(this.response.status.value)
        AppError.ApiException.ServerException(this)
    }

    is ChannelReadException -> AppError.ApiException.NetworkException(this)

    is TimeoutCancellationException,
    is HttpRequestTimeoutException,
    is SocketTimeoutException,
    -> AppError.ApiException.TimeoutException(this)

    else -> AppError.UnknownException(this)
}
