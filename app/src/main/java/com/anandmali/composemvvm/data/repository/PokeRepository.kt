package com.anandmali.composemvvm.data.repository

import com.anandmali.composemvvm.data.source.network.PokemonViewDTO

interface PokeRepository {
    suspend fun getPokeList(): List<PokemonViewDTO>
}