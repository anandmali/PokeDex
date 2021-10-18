package com.anandm.composeview.network

import com.anandm.composeview.network.data.PokeData

interface PokeRepository {
    suspend fun getPokes(): List<PokeData>
}