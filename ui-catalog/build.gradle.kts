plugins {
    id("baseComposeProject.primitive.androidapplication")
    id("baseComposeProject.primitive.android.kotlin")
    id("baseComposeProject.primitive.android.compose")
}

android {
    namespace = "com.example.uicatalog"
    defaultConfig {
        applicationId = "com.example.uicatalog"
        versionCode = 1
        versionName = "1.0"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
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
    implementation(projects.core.ui)
}
