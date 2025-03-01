package com.example.build_logic.primitive

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.systemProperties

class AndroidRoborazziPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("io.github.takahirom.roborazzi")

            }

            android {
                testOptions {
                    unitTests {
                        // Whether unmocked methods from android.jar should throw exceptions or return default values
                        isIncludeAndroidResources = true
                        // Configures all unit testing tasks.
                        all {
                            it.systemProperties(
                                "roborazzi.output.dir" to rootProject.file("screenshots").absolutePath,
                                "robolectric.pixelCopyRenderMode" to "hardware",
                            )
                        }
                    }
                }
            }

            dependencies {
                testImplementation(libs.library("roborazzi"))
                testImplementation(libs.library("roborazziCompose"))
                testImplementation(libs.library("roborazziPreviewScannerSupport"))
                testImplementation(libs.library("composablePreviewScanner"))
                testImplementation(libs.library("robolectric"))
            }
        }
    }
}