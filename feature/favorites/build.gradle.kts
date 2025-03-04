plugins {
    id("baseComposeProject.primitive.android")
    id("baseComposeProject.primitive.android.kotlin")
    id("baseComposeProject.primitive.android.compose")
    id("baseComposeProject.primitive.android.hilt")
    id("baseComposeProject.primitive.detekt")
}

android.namespace = "com.example.basecomposeproject.feature.favorites"

dependencies {
    implementation(projects.core.design)
    implementation(projects.core.ui)
    implementation(projects.core.common)
    implementation(projects.core.data)
    implementation(projects.core.model)
    implementation(projects.feature.utils)
}
