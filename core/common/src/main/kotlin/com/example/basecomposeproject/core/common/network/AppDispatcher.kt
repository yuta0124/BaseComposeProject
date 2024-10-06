package com.example.basecomposeproject.core.common.network

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class AppDispatcher(val appDispatcher: AppDispatchers)

enum class AppDispatchers {
    IO,
    DEFAULT,
    MAIN,
}
