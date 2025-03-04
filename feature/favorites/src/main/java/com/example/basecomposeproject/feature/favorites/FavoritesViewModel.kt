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
}
