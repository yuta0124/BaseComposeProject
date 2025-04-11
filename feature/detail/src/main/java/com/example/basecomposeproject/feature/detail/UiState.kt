package com.example.basecomposeproject.feature.detail

import arrow.optics.optics

@optics
data class UiState(
    val name: String = "",
    val imagePath: String = "",
) {
    companion object
}
