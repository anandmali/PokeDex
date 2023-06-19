package com.anandmali.composemvvm.data.repository

import com.anandmali.composemvvm.data.source.network.PokeApi
import com.anandmali.composemvvm.data.source.network.Pokemon
import com.anandmali.composemvvm.data.source.network.PokemonViewDTO
import com.anandmali.composemvvm.data.source.network.toViewData
import javax.inject.Inject

private const val LIMIT = 20
private const val OFFSET = 0

class PokeRepositoryImpl @Inject constructor(private val pokeApi: PokeApi) : PokeRepository {

    override suspend fun getPokeList(): List<PokemonViewDTO> {
        val result = getPokemonList()
        return mapPokemonList(result)
    }

    private suspend fun getPokemonList(): List<Pokemon> {
        val response = pokeApi.getPokeList(LIMIT, OFFSET)
        if (response.isSuccessful) {
            response.body()?.let { body -> return body.results } ?: run { return emptyList() }
        }
        return emptyList()
    }

    private fun mapPokemonList(result: List<Pokemon>): List<PokemonViewDTO> {
        return result.map { it.toViewData() }
    }
}