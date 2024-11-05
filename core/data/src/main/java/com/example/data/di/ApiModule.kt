package com.example.data.di

import com.example.data.defaultJson
import com.example.data.defaultKtorConfig
import com.example.model.BuildConfigProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.HEADERS
import javax.inject.Singleton

// TODO: ステータスコードを元に独自のエラー型に変える
@InstallIn(SingletonComponent::class)
@Module
object ApiModule {
    @Provides
    @Singleton
    fun provideKtorfit(
        httpClient: HttpClient,
        serverEnvironment: ServerEnvironmentModule.ServerEnvironment,
    ): Ktorfit = Ktorfit.Builder().httpClient(httpClient).baseUrl(serverEnvironment.baseUrl).build()

    @Provides
    @Singleton
    fun provideHttpClient(
        okHttpClient: OkHttpClient,
        ktorJsonSettings: Json,
        buildConfigProvider: BuildConfigProvider,
    ): HttpClient = HttpClient(OkHttp) {
        // https://ktor.io/docs/client-engines.html
        engine {
            config {
                preconfigured = okHttpClient
                addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = if (buildConfigProvider.debugBuild) {
                            HttpLoggingInterceptor.Level.BODY
                        } else {
                            HttpLoggingInterceptor.Level.NONE
                        }
                    }
                )
            }
        }
        defaultKtorConfig(ktorJsonSettings)
    }

    @Provides
    @Singleton
    fun provideKtorSettings(): Json = defaultJson()

    @Provides
    @Singleton
    fun okHttpClient(buildConfigProvider: BuildConfigProvider): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (buildConfigProvider.debugBuild) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HEADERS
            // https://bbluecoder.medium.com/a-deep-dive-into-okhttp-interceptors-8fd8d79869ec
            builder.addNetworkInterceptor(httpLoggingInterceptor)
        }
        return builder.build()
    }
}
