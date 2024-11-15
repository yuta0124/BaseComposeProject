package com.example.basecomposeproject.feature.search

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.example.data.network.repository.impl.PokemonRepository
import com.example.model.Pokemon
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.retained.rememberRetained
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.components.ActivityComponent
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.launch

@Suppress("UnusedPrivateProperty")
class SearchPresenter @AssistedInject constructor(
    @Assisted private val navigator: Navigator,
    private val pokemonRepository: PokemonRepository,
) : Presenter<UiState> {
    @CircuitInject(SearchScreen::class, ActivityComponent::class)
    @AssistedFactory
    interface Factory {
        fun create(navigator: Navigator): SearchPresenter
    }

    @Suppress("MagicNumber")
    @Composable
    override fun present(): UiState {
        val coroutineScope = rememberCoroutineScope()
        var list: PersistentList<Pokemon> by rememberRetained {
            mutableStateOf(persistentListOf())
        }

        return UiState(pokemons = list) { event ->
            when (event) {
                Event.FetchList -> {
                    coroutineScope.launch {
                        runCatching {
                            pokemonRepository.getPokemons(
                                limit = null,
                                offset = null,
                            )
                        }.onSuccess {
                            list = it.pokemons
                        }.onFailure {
                            // TODO: エラーハンドリング
                            Log.d("test", it.message.toString())
                        }
                    }
                }

                Event.GoToDetail -> {
                    // TODO: 詳細画面に遷移する
                }
            }
        }
    }
}
