plugins {
    id("baseComposeProject.primitive.android")
    id("baseComposeProject.primitive.android.kotlin")
    id("baseComposeProject.primitive.android.compose")
    id("baseComposeProject.primitive.android.hilt")
    id("baseComposeProject.primitive.detekt")
}

android.namespace = "com.example.basecomposeproject.core.testing"

dependencies {
    implementation(libs.androidxCoreKtx)
    implementation(libs.androidxAppcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    testImplementation(libs.truth)
    testImplementation(libs.kotlinTest)
    testImplementation(libs.kotlinxCoroutinesTest)
    androidTestImplementation(libs.androidxJunit)
    androidTestImplementation(libs.androidxEspressoCore)
}