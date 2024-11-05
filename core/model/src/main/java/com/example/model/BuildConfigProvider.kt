package com.example.model

interface BuildConfigProvider {
    val versionName: String
    val  debugBuild: Boolean
}