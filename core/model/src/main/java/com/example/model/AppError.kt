package com.example.model

sealed class AppError : RuntimeException {
    private constructor(message: String?, cause: Throwable?) : super(message, cause)
    private constructor(cause: Throwable?) : super(cause)

    sealed class ApiException(cause: Throwable?) : AppError(cause) {
        class NetworkException(cause: Throwable?) : ApiException(cause)
        class ServerException(cause: Throwable?) : ApiException(cause)
        class TimeoutException(cause: Throwable?) : ApiException(cause)
        class SessionNotFountException(cause: Throwable?) : ApiException(cause)
        class UnknownException(cause: Throwable?) : ApiException(cause)
    }

    class UnknownException(cause: Throwable?) : AppError(cause)
    /**
     * TODO: パースを用いる独自エラー定義
     * https://github.com/DroidKaigi/conference-app-2024/blob/main/core/model/src/commonMain/kotlin/io/github/droidkaigi/confsched/model/AppError.kt
     */
}