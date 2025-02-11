package com.anandmali.pokedex.data.repository

import androidx.paging.PagingData
import com.anandmali.pokedex.data.Resource
import com.anandmali.pokedex.data.source.network.response.PokeDetailsResponse
import com.anandmali.pokedex.data.source.network.response.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokeRepository {
     fun getPokeList(): Flow<PagingData<Pokemon>>

    suspend fun getPokemonInfo(pokemonName: String): Resource<PokeDetailsResponse>
}