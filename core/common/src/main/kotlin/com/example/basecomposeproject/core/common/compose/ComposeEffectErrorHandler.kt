package com.example.basecomposeproject.core.common.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.InternalComposeApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.ProvidedValue
import androidx.compose.runtime.State
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.staticCompositionLocalOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

@Composable
fun SafeLaunchedEffect(key: Any?, block: suspend CoroutineScope.() -> Unit) {
    LaunchedEffect(key) {
        try {
            block()
        } catch (e: Exception) {
            /**
             * キャンセルされていれば「CancellationException」をスローする
             */
            ensureActive()
            e.printStackTrace()
        }
    }
}

@Composable
fun <T : R, R> Flow<T>.safeCollectAsRetainedState(
    initial: R,
    context: CoroutineContext = EmptyCoroutineContext,
): State<R> {
    // See collectAsState
    return produceRetainedState(initial, this, context) {
        try {
            if (context == EmptyCoroutineContext) {
                collect { value = it }
            } else {
                withContext(context) {
                    collect { value = it }
                }
            }
        } catch (e: CancellationException) {
            throw e
        } catch (e: Throwable) {
            composeEffectErrorHandler.emit(e)
        }
    }
}

@Suppress("StateFlowValueCalledInComposition")
@Composable
fun <T : R, R> StateFlow<T>.safeCollectAsRetainedState(
    context: CoroutineContext = EmptyCoroutineContext,
): State<R> {
    return safeCollectAsRetainedState(value, context)
}