package com.example.data

import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * tokenを必要とするAPI通信で利用する
 */

// fun HttpClientConfig<*>.defaultKtorConfig(
//    userDataStore: sampleDataStore,
//    ktorJsonSetting: Json,
// ) {
//    install(ContentNegotiation) {
//        json(
//            ktorJsonSetting
//        )
//    }
//
//    defaultRequest {
//        headers {
//            userDataStore.token?.let {
//                set("Authorization", "Bearer $it")
//            }
//        }
//    }
// }

internal fun HttpClientConfig<*>.defaultKtorConfig(
    ktorJsonSetting: Json,
) {
    install(ContentNegotiation) {
        json(
            ktorJsonSetting
        )
    }

    defaultRequest {
        headers {
            contentType(ContentType.Application.Json)
        }
    }
}

internal fun defaultJson(): Json {
    return Json {
        isLenient = true
        prettyPrint = true
        ignoreUnknownKeys = true
    }
}
