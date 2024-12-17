package com.example.data.network.repository

import androidx.compose.runtime.compositionLocalOf
import kotlin.reflect.KClass

@Suppress("CompositionLocalAllowlist")
val LocalRepositories = compositionLocalOf<Map<KClass<*>, Any>> {
    error("No LocalRepository provided")
}
