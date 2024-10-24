package com.example.build_logic.primitive

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("com.dropbox.dependency-guard")
            }

            androidLibrary {
                setupAndroid()
            }

            dependencyGuard {
                configuration("releaseRuntimeClasspath")
            }
        }
    }
}