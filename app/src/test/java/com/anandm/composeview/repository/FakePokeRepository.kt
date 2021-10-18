package com.anandm.composeview.repository

import com.anandm.composeview.mockPokeData
import com.anandm.composeview.network.PokeRepository
import com.anandm.composeview.network.data.PokeData

class FakePokeRepository : PokeRepository {
    override suspend fun getPokes(): List<PokeData> {
        return listOf(mockPokeData())
    }
}