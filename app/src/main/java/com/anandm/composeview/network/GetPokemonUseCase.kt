package com.anandm.composeview.network

import com.anandm.composeview.network.data.PokemonData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPokemonUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {

    operator fun invoke(): Flow<List<PokemonData>> = flow {
        try {
            emit(pokemonRepository.getPokes())
        } catch (exception: Exception) {
            emit(emptyArray<PokemonData>().asList())
        }
    }
}