package com.example.data.di

import com.example.model.BuildConfigProvider
import dagger.BindsOptionalOf
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.Optional
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class AppAndroidBuildConfig

@InstallIn(SingletonComponent::class)
@Module
object BuildConfigProviderModule {
    @Provides
    @Singleton
    fun provideBuildConfigProvider(
        @AppAndroidBuildConfig buildConfigOverride: Optional<BuildConfigProvider>,
    ): BuildConfigProvider = if (buildConfigOverride.isPresent) {
        buildConfigOverride.get()
    } else {
        EmptyBuildConfigProvider
    }
}

@InstallIn(SingletonComponent::class)
@Module
abstract class AppAndroidBuildConfigModule {
    @BindsOptionalOf
    @AppAndroidBuildConfig
    abstract fun bindBuildConfigProvider(): BuildConfigProvider
}

private object EmptyBuildConfigProvider : BuildConfigProvider {
    override val versionName: String = ""
    override val debugBuild: Boolean = false
}
