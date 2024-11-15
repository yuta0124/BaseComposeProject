package com.example.basecomposeproject

import com.example.data.di.AppAndroidBuildConfig
import com.example.model.BuildConfigProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    @AppAndroidBuildConfig
    fun provideBuildConfigProvider(): BuildConfigProvider = AppBuildConfigProvider()
}

class AppBuildConfigProvider(
    override val versionName: String = BuildConfig.VERSION_NAME,
    override val debugBuild: Boolean = BuildConfig.DEBUG,
) : BuildConfigProvider
