package com.example.data.network.repository.impl

import arrow.core.Either
import com.example.basecomposeproject.core.common.network.AppDispatcher
import com.example.basecomposeproject.core.common.network.AppDispatchers
import com.example.data.network.pokemon.PokemonApi
import com.example.data.network.pokemon.response.PokemonsResponse
import com.example.data.network.repository.IPokemonRepository
import com.example.model.AppError
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.ResponseException
import io.ktor.util.cio.ChannelReadException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepository @Inject constructor(
    private val pokemonApi: PokemonApi,
    @AppDispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : IPokemonRepository {
    override suspend fun getPokemons(
        limit: Int?,
        offset: Int?
    ): Either<AppError, PokemonsResponse> = withContext(ioDispatcher) {
        pokemonApi.getPokemons(limit, offset).mapLeft {
            it.toAppError()
        }
    }
}

private fun Throwable.toAppError(): AppError = when (this) {
    is AppError -> this
    is ResponseException -> {
        // TODO: ステータスコードにてパースした独自エラーを返す
        // when(this.response.status.value)
        AppError.ApiException.ServerException(this)
    }

    is ChannelReadException -> AppError.ApiException.NetworkException(this)

    is TimeoutCancellationException,
    is HttpRequestTimeoutException,
    is SocketTimeoutException,
    -> AppError.ApiException.TimeoutException(this)

    else -> AppError.UnknownException(this)
}
