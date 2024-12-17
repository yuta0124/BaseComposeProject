package com.example.build_logic.primitive

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class AndroidComposePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("kotlin-parcelize")
                apply("org.jetbrains.kotlin.plugin.compose")
            }
            android {
                buildFeatures.compose = true
            }
            composeCompiler {
                enableStrongSkippingMode.set(true)
            }

            dependencies {
                implementation(platform(libs.library("composeBom")))
                implementation(libs.library("androidxCoreKtx"))
                implementation(libs.library("androidxActivityCompose"))
                implementation(libs.library("androidxLifecycleRuntimeKtx"))
                implementation(libs.library("androidxLifecycleRuntimeCompose"))
                implementation(libs.library("composeUi"))
                implementation(libs.library("composeMaterial3"))
                implementation(libs.library("composeUiToolingPreview"))
                implementation(libs.library("androidxLifecycleRuntimeKtx"))
                implementation(libs.library("androidxActivityCompose"))
                implementation(libs.library("rin"))
                testImplementation(libs.library("junit"))
                testImplementation(libs.library("androidxJunit"))
                testImplementation(libs.library("androidxEspressoCore"))
                testImplementation(libs.library("composeUiTestJunit4"))
                debugImplementation(libs.library("composeUiTooling"))
                debugImplementation(libs.library("composeUiTestManifest"))
            }
        }
    }
}