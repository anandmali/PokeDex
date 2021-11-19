package com.anandm.composeview.network

import com.anandm.composeview.network.data.PokemonData
import com.anandm.composeview.network.data.PokemonListData
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val apiService: PokemonApiService,
    private val pokemonListMapper: PokemonListMapperImpl
) : PokemonRepository {

    override suspend fun getPokes(): List<PokemonData> {
        val response = apiService.getPokeList(10, 0)
        if (response.isSuccessful) {
            response.body()?.let {
                return mapPokemonList(it)
            }
        }
        return emptyList()
    }

    private fun mapPokemonList(pokemonListData: PokemonListData): List<PokemonData> {
        return pokemonListMapper.map(pokemonListData)
    }
}