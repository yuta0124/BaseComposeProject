package com.example.basecomposeproject.feature.detail

sealed interface DetailIntent {
    data object SwitchFavorite : DetailIntent
}
