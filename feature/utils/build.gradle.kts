plugins {
    id("baseComposeProject.primitive.android")
    id("baseComposeProject.primitive.android.kotlin")
    id("baseComposeProject.primitive.detekt")
}

android.namespace = "com.example.basecomposeproject.feature.utils"

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.model)
}
