package com.anandm.composeview.network

import com.anandm.composeview.network.data.PokemonListData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonApiService {

    @GET("pokemon")
    suspend fun getPokeList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): Response<PokemonListData>

}