package com.example.build_logic.convention

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class AndroidFeaturePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("droid2024app.primitive.androidapplication")
                apply("droid2024app.primitive.android.kotlin")
                apply("droid2024app.primitive.android.compose")
                apply("droid2024app.primitive.android.hilt")
                apply("droid2024app.primitive.detekt")
            }

            tasks.withType<KotlinCompile>().configureEach {
                compilerOptions {
                    buildComposeMetricsParameters().onEach {
                        freeCompilerArgs.add(it)
                    }
                }
            }
        }
    }
}

/**
 * Compose Compilerのパフォーマンスと効率を測定するメトリクスで、パフォーマンスを最適化するために改善できる部分を特定するために使用する
 * https://speakerdeck.com/makun/compose-compiler-metrics-xiang-xi-tohuo-yong-fang-fa?slide=12
 */
fun Project.buildComposeMetricsParameters(): List<String> {
    val metricParameters = mutableListOf<String>()
    val enableMetricsProvider = project.providers.gradleProperty("enableComposeCompilerMetrics")
    val relativePath = projectDir.relativeTo(rootDir)

    val enableMetrics = (enableMetricsProvider.orNull == "true")
    if (enableMetrics) {
        val metricsFolder = rootProject.buildDir.resolve("compose-metrics").resolve(relativePath)
        metricParameters.add("-P")
        metricParameters.add(
            "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=" + metricsFolder.absolutePath
        )
    }

    val enableReportsProvider = project.providers.gradleProperty("enableComposeCompilerReports")
    val enableReports = (enableReportsProvider.orNull == "true")
    if (enableReports) {
        val reportsFolder = rootProject.buildDir.resolve("compose-reports").resolve(relativePath)
        metricParameters.add("-P")
        metricParameters.add(
            "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=" + reportsFolder.absolutePath
        )
    }
    return metricParameters.toList()
}
