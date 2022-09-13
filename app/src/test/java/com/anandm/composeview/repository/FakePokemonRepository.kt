package com.anandm.composeview.repository

import com.anandm.composeview.mockPokeViewData
import com.anandm.composeview.network.RemoteRepository
import com.anandm.composeview.network.data.PokemonViewDTO

class FakePokemonRepository : RemoteRepository {
    override suspend fun getPokes(): List<PokemonViewDTO> {
        return listOf(mockPokeViewData())
    }
}