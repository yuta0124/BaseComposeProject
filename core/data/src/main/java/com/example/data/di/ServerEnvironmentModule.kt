package com.example.data.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ServerEnvironmentModule {
    class ServerEnvironment(
        internal val baseUrl: String,
    )

    public interface HasServerEnvironment {
        public val serverEnvironment: ServerEnvironment
    }

    @Provides
    @Singleton
    fun provideServerEnvironment(application: Application): ServerEnvironment =
        (application as HasServerEnvironment).serverEnvironment
}
