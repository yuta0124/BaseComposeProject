plugins {
    id("baseComposeProject.primitive.android")
    id("baseComposeProject.primitive.android.kotlin")
    id("baseComposeProject.primitive.android.compose")
    id("baseComposeProject.primitive.android.hilt")
    id("baseComposeProject.primitive.detekt")
}

android {
    namespace = "com.example.basecomposeproject.core.ui"

    dependencies {
        implementation(projects.core.model)
        implementation(projects.core.design)
    }
}
