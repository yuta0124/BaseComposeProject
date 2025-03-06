package com.example.build_logic.primitive

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class RoomPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.devtools.ksp")
            }

            dependencies {
                implementation(libs.library("roomKtx"))
                implementation(libs.library("roomRuntime"))
                ksp(libs.library("roomCompiler"))
            }
        }
    }
}