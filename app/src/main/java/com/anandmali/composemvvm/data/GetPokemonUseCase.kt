package com.anandmali.composemvvm.data

import com.anandmali.composemvvm.data.repository.PokeRepository
import com.anandmali.composemvvm.data.source.network.PokemonViewDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPokemonUseCase @Inject constructor(
    private val remoteRepository: PokeRepository
) {

    operator fun invoke(): Flow<List<PokemonViewDTO>> = flow {
        try {
            emit(remoteRepository.getPokeList())
        } catch (exception: Exception) {
            emit(emptyArray<PokemonViewDTO>().asList())
        }
    }
}