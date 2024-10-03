package com.example.build_logic.primitive

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidHiltPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.devtools.ksp")
                apply("dagger.hilt.android.plugin")
            }
            dependencies {
                implementation(libs.library("daggerHiltAndroid"))
                ksp(libs.library("daggerHiltAndroidCompiler"))
            }
        }
    }
}