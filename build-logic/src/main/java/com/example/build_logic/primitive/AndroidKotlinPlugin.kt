package com.example.build_logic.primitive

import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidKotlinPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.plugin.serialization")
                apply("com.google.devtools.ksp")
            }
            tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java) {
                kotlinOptions.jvmTarget = "11"
            }

            android {
                kotlinOptions {
                    // Treat all Kotlin warnings as errors (disabled by default)
                    allWarningsAsErrors = properties["warningsAsErrors"] as? Boolean ?: false

                    freeCompilerArgs = freeCompilerArgs + listOf(
//              "-opt-in=kotlin.RequiresOptIn",
                        // Enable experimental coroutines APIs, including Flow
//              "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                        "-Xcontext-receivers"
                    )

                    jvmTarget = JavaVersion.VERSION_11.toString()
                }
            }

            dependencies {
                implementation(libs.library("arrowCore"))
                implementation(libs.library("arrowOptics"))
                ksp(libs.library("arrowOpticsKsp"))
                implementation(libs.library("kotlinSerializationJson"))
            }
        }
    }
}