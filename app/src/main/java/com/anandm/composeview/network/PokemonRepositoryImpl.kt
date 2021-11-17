package com.anandm.composeview.network

import com.anandm.composeview.network.data.PokemonData
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val apiService: PokemonApiService
) : PokemonRepository {

    override suspend fun getPokes(): List<PokemonData> {
        val response = apiService.getPokeList(10, 0)
        if (response.isSuccessful) {
            response.body()?.let {
                return it.results
            }
        }
        return emptyList()
    }

}