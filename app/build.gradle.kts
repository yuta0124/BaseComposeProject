plugins {
    id("baseComposeProject.primitive.androidapplication")
    id("baseComposeProject.primitive.android.kotlin")
    id("baseComposeProject.primitive.android.compose")
    id("baseComposeProject.primitive.android.hilt")
    id("baseComposeProject.primitive.detekt")
//    id("baseComposeProject.convention.androidfeature")
}

android {
    val network = "network"

    namespace = "com.example.basecomposeproject"

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

    defaultConfig {
        applicationId = "com.example.basecomposeproject"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
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
}

dependencies {
    implementation(projects.core.design)
    implementation(projects.feature.search)
    implementation(projects.core.data)
    implementation(projects.core.model)
}
