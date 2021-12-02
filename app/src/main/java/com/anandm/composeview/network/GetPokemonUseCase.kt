package com.anandm.composeview.network

import com.anandm.composeview.network.data.PokemonViewDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPokemonUseCase @Inject constructor(
    private val remoteRepository: RemoteRepository
) {

    operator fun invoke(): Flow<List<PokemonViewDTO>> = flow {
        try {
            emit(remoteRepository.getPokes())
        } catch (exception: Exception) {
            emit(emptyArray<PokemonViewDTO>().asList())
        }
    }
}