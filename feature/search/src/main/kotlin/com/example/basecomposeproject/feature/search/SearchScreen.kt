package com.example.basecomposeproject.feature.search

import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize

@Parcelize
data object SearchScreen : Screen

data class UiState(
    val list: List<String>,
    val eventSink: (Event) -> Unit,
) : CircuitUiState

sealed interface Event : CircuitUiEvent {
    data object FetchList : Event
    data object GoToDetail : Event
}
