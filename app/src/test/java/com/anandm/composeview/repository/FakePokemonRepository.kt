package com.anandm.composeview.repository

import com.anandm.composeview.mockPokeData
import com.anandm.composeview.network.RemoteRepository
import com.anandm.composeview.network.data.PokemonDTO

class FakePokemonRepository : RemoteRepository {
    override suspend fun getPokes(): List<PokemonDTO> {
        return listOf(mockPokeData())
    }
}