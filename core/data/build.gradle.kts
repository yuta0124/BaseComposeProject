import com.example.build_logic.primitive.implementation

plugins {
    id("baseComposeProject.primitive.android")
    id("baseComposeProject.primitive.android.kotlin")
    id("baseComposeProject.primitive.android.hilt")
    id("baseComposeProject.primitive.detekt")
    id("baseComposeProject.primitive.ktorfit")
    id("baseComposeProject.primitive.android.compose")
}

android {
    namespace = "com.example.basecomposeproject.core.data"

    dependencies {
        implementation(projects.core.model)
        implementation(projects.core.common)
    }
}
