package com.anandm.composeview.network

import com.anandm.composeview.network.data.PokeList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PokeApiService {

    @GET("pokemon")
    suspend fun getPokeList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): Response<PokeList>

}