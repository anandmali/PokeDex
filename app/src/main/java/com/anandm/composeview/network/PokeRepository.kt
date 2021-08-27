package com.anandm.composeview.network

import javax.inject.Inject

class PokeRepository @Inject constructor(private val apiService: PokeApiService) {

    suspend fun getPokemonList() =
        makeApiCall(apiService.getPokemonList(20, 0))

}