package com.example.data.di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.example.data.network.repository.LocalRepositories
import javax.inject.Inject
import javax.inject.Qualifier

@Qualifier
annotation class RepositoryQualifier

class RepositoryProvider @Inject constructor(
    @RepositoryQualifier repositories: Map<Class<out Any>, @JvmSuppressWildcards Any>,
) {
    private val repositoriesMap = repositories
        .map { (k, v) ->
            k.kotlin to v as Any
        }.toMap()

    @Composable
    public fun Provide(content: @Composable () -> Unit) {
        CompositionLocalProvider(
            LocalRepositories provides repositoriesMap,
        ) {
            content()
        }
    }
}
