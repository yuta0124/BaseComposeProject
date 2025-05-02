plugins {
    id("baseComposeProject.primitive.android")
    id("baseComposeProject.primitive.android.kotlin")
    id("baseComposeProject.primitive.android.compose")
    id("baseComposeProject.primitive.android.hilt")
    id("baseComposeProject.primitive.detekt")
}

android.namespace = "com.example.basecomposeproject.core.testing"

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.data)
    implementation(projects.core.design)
    implementation(projects.core.common)
    implementation(projects.feature.search)
    implementation(projects.feature.favorites)
    implementation(projects.feature.detail)
}
