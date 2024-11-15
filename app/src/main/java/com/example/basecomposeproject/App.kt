package com.example.basecomposeproject

import android.app.Application
import com.example.data.di.ServerEnvironmentModule
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application(), ServerEnvironmentModule.HasServerEnvironment {
    override val serverEnvironment: ServerEnvironmentModule.ServerEnvironment
        get() = ServerEnvironmentModule.ServerEnvironment(
            BuildConfig.SERVER_URL
        )
}
