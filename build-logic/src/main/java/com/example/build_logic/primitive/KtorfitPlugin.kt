package com.example.build_logic.primitive

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class KtorfitPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("de.jensklingenberg.ktorfit")
                apply("com.google.devtools.ksp")
            }

            dependencies {
                implementation(libs.library("ktorfitLib"))
                implementation(libs.library("ktorClientCore"))
                implementation(libs.library("ktorClientOkHttp"))
                implementation(libs.library("ktorClientLogging"))
                implementation(libs.library("okHttpLoggingInterceptor"))
                implementation(libs.library("kotlinSerializationJson"))
                implementation(libs.library("ktorContentNegotiation"))
                implementation(libs.library("ktorKotlinxSerialization"))
            }
        }
    }
}