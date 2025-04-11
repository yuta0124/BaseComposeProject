package com.example.basecomposeproject.feature.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        // TODO: 詳細取得
        _uiState.update {
            it.copy(
                name = savedStateHandle.toRoute<Detail>().name
            )
        }
    }

    fun onAction(intent: DetailIntent) {
        when (intent) {
            DetailIntent.SwitchFavorite -> {
                // TODO: お気に入り登録変更
            }
        }
    }
}
