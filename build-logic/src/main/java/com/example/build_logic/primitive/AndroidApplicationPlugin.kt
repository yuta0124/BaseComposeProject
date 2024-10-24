package com.example.build_logic.primitive

import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("com.dropbox.dependency-guard")
            }

            androidApplication {
                setupAndroid()
            }

            dependencyGuard {
                configuration("releaseRuntimeClasspath")
            }
        }
    }
}