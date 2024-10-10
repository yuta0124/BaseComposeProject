package com.example.basecomposeproject.feature.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.retained.rememberRetained
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.components.ActivityComponent

@Suppress("UnusedPrivateProperty")
class SearchPresenter @AssistedInject constructor(
    @Assisted private val navigator: Navigator,
) : Presenter<UiState> {
    @CircuitInject(SearchScreen::class, ActivityComponent::class)
    @AssistedFactory
    interface Factory {
        fun create(navigator: Navigator): SearchPresenter
    }

    @Suppress("MagicNumber")
    @Composable
    override fun present(): UiState {
        var list: List<String> by rememberRetained {
            mutableStateOf(emptyList())
        }

        return UiState(list = list) { event ->
            when (event) {
                Event.FetchList -> {
                    // TODO: APIにてリストを取得する
                    list = (0 until 10).map {
                        "item$it"
                    }
                }

                Event.GoToDetail -> {
                    // TODO: 詳細画面に遷移する
                }
            }
        }
    }
}
