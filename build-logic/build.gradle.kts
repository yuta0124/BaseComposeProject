import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.example.baseComposeProject.buildlogic"

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions.jvmTarget = "17"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.bundles.plugins)
    // https://github.com/google/dagger/issues/3068#issuecomment-1470534930
    implementation(libs.javaPoet)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "baseComposeProject.primitive.androidapplication"
            implementationClass = "com.example.build_logic.primitive.AndroidApplicationPlugin"
        }
        register("android") {
            id = "baseComposeProject.primitive.android"
            implementationClass = "com.example.build_logic.primitive.AndroidPlugin"
        }
        register("androidKotlin") {
            id = "baseComposeProject.primitive.android.kotlin"
            implementationClass = "com.example.build_logic.primitive.AndroidKotlinPlugin"
        }
        register("androidCompose") {
            id = "baseComposeProject.primitive.android.compose"
            implementationClass = "com.example.build_logic.primitive.AndroidComposePlugin"
        }
        register("androidHilt") {
            id = "baseComposeProject.primitive.android.hilt"
            implementationClass = "com.example.build_logic.primitive.AndroidHiltPlugin"
        }
        register("detekt") {
            id = "baseComposeProject.primitive.detekt"
            implementationClass = "com.example.build_logic.primitive.DetektPlugin"
        }
        register("androidFeature") {
            id = "baseComposeProject.convention.androidfeature"
            implementationClass = "com.example.build_logic.convention.AndroidFeaturePlugin"
        }
    }
}
