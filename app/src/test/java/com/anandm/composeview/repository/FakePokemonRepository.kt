package com.anandm.composeview.repository

import com.anandm.composeview.mockPokeData
import com.anandm.composeview.network.PokemonRepository
import com.anandm.composeview.network.data.PokemonData

class FakePokemonRepository : PokemonRepository {
    override suspend fun getPokes(): List<PokemonData> {
        return listOf(mockPokeData())
    }
}