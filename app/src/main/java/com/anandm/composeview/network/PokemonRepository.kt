package com.anandm.composeview.network

import com.anandm.composeview.network.data.PokemonData

interface PokemonRepository {
    suspend fun getPokes(): List<PokemonData>
}