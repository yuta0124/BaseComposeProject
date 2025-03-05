package com.example.basecomposeproject.feature.favorites

import androidx.lifecycle.ViewModel
import arrow.optics.optics
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@optics
data class UiState(
    val isLoading: Boolean = true,
) {
    companion object
}

@HiltViewModel
class FavoritesViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    fun onAction(intent: FavoritesIntent) = when (intent) {
        is FavoritesIntent.SwitchFavorite -> switchFavorite(intent.name)
    }

    @Suppress("UnusedParameter")
    private fun switchFavorite(name: String) {
        // TODO: お気に入り状態切り替え処理
    }
}
