import com.example.build_logic.primitive.implementation

plugins {
    id("baseComposeProject.primitive.android")
    id("baseComposeProject.primitive.android.kotlin")
    id("baseComposeProject.primitive.android.compose")
    id("baseComposeProject.primitive.android.hilt")
    id("baseComposeProject.primitive.detekt")
}

android {
    namespace = "com.example.basecomposeproject.feature.search"
}

dependencies {
    implementation(projects.core.design)
    implementation(projects.core.common)
}