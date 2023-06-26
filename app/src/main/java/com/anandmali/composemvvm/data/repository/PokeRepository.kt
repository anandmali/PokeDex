package com.anandmali.composemvvm.data.repository

import com.anandmali.composemvvm.data.source.network.PokemonViewDTO
import com.anandmali.composemvvm.data.source.network.response.PokeDetailsResponse
import com.anandmali.composemvvm.data.Resource

interface PokeRepository {
    suspend fun getPokeList(): List<PokemonViewDTO>

    suspend fun getPokemonInfo(pokemonName: String): Resource<PokeDetailsResponse>
}