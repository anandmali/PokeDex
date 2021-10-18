package com.anandm.composeview.network

import com.anandm.composeview.network.data.PokeData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPokesUseCase @Inject constructor(
    private val pokeRepository: PokeRepository
) {

    operator fun invoke(): Flow<List<PokeData>> = flow {
        try {
            emit(pokeRepository.getPokes())
        } catch (exception: Exception) {
            emit(emptyArray<PokeData>().asList())
        }
    }
}