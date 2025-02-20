// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidGradlePlugin) apply false
    alias(libs.plugins.kotlinGradlePlugin) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.androidGradleLibraryPlugin) apply false
    alias(libs.plugins.roborazziGradlePlugin) apply false
}

buildscript {
    configurations.all {
        resolutionStrategy.eachDependency {
            when {
                requested.name == "javapoet" -> useVersion("1.13.0")
            }
        }
    }
}
