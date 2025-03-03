import com.github.takahirom.roborazzi.ExperimentalRoborazziApi

plugins {
    id("baseComposeProject.primitive.android")
    id("baseComposeProject.primitive.android.kotlin")
    id("baseComposeProject.primitive.android.compose")
    id("baseComposeProject.primitive.android.hilt")
    id("baseComposeProject.primitive.detekt")
    id("baseComposeProject.primitive.screenshotTestPlugin")
}

android.namespace = "com.example.basecomposeproject.core.testing"

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.data)
    implementation(projects.core.design)
    implementation(projects.core.common)
    implementation(projects.feature.search)
}

/**
 * roborazzi {
 *   @OptIn(ExperimentalRoborazziApi::class)
 *   generateComposePreviewRobolectricTests {
 *     enable = true
 *     // The package names to scan for Composable Previews.
 *     packages = listOf("com.example")
 *     // robolectricConfig will be passed to Robolectric's @Config annotation in the generated test class.
 *     // See https://robolectric.org/configuring/ for more information.
 *     robolectricConfig = mapOf(
 *       "sdk" to "[32]",
 *       "qualifiers" to "RobolectricDeviceQualifiers.Pixel5",
 *     )
 *     // If true, the private previews will be included in the test.
 *     includePrivatePreviews = true
 *     // The fully qualified class name of the custom test class that implements
 *          [com.github.takahirom.roborazzi.ComposePreviewTester].
 *     testerQualifiedClassName = "com.example.MyCustomComposePreviewTester"
 *   }
 * }
 */
roborazzi {
    outputDir.set(rootProject.file("screenshots"))

    @OptIn(ExperimentalRoborazziApi::class)
    generateComposePreviewRobolectricTests {
        enable = true

        testerQualifiedClassName = "com.example.testing.BaseComposePreviewTester"

        packages = listOf("com.example.basecomposeproject")
    }
}
