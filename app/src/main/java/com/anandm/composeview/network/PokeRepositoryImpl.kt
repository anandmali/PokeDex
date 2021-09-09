package com.anandm.composeview.network

import com.anandm.composeview.network.data.PokeData
import javax.inject.Inject

class PokeRepositoryImpl @Inject constructor(
    private val apiService: PokeApiService
) : PokeRepository {

    override suspend fun getPokes(): List<PokeData> {
        val response = apiService.getPokeList(20, 0)
        if (response.isSuccessful) {
            response.body()?.let {
                return it.results
            }
        }
        return emptyList()
    }

}