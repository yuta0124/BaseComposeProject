import com.github.takahirom.roborazzi.ExperimentalRoborazziApi

plugins {
    id("baseComposeProject.primitive.androidapplication")
    id("baseComposeProject.primitive.android.kotlin")
    id("baseComposeProject.primitive.android.compose")
    id("baseComposeProject.primitive.android.hilt")
    id("baseComposeProject.primitive.detekt")
    alias(libs.plugins.roborazziGradlePlugin)
//    id("baseComposeProject.convention.androidfeature")
}

android {
    val network = "network"
    namespace = "com.example.basecomposeproject"
    defaultConfig {
        applicationId = "com.example.basecomposeproject"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    flavorDimensions += network
    buildFeatures {
        buildConfig = true
    }

    productFlavors {
        create("dev") {
            isDefault = true
            applicationIdSuffix = ".dev"
            dimension = network
            buildConfigField(
                type = "String",
                name = "SERVER_URL",
                value = "\"https://pokeapi.co/api/v2/\"",
            )
        }
        create("prod") {
            applicationId = ".prod"
            dimension = network
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

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
    implementation(projects.core.design)
    implementation(projects.feature.search)
    implementation(projects.core.data)
    implementation(projects.core.model)

    testImplementation(libs.roborazzi)
    testImplementation(libs.roborazziCompose)
    testImplementation(libs.roborazziPreviewScannerSupport)
    testImplementation(libs.composablePreviewScanner)
    testImplementation(libs.robolectric)
}

roborazzi {
    outputDir.set(rootProject.file("screenshots"))

    @OptIn(ExperimentalRoborazziApi::class)
    generateComposePreviewRobolectricTests {
        enable = true

        packages = listOf("com.example.baseComposeProject")
    }
}
