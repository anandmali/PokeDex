package com.anandm.composeview.network

import com.anandm.composeview.network.data.PokemonViewDTO

interface RemoteRepository {
    suspend fun getPokes(): List<PokemonViewDTO>
}