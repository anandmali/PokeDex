package com.anandm.composeview.network

import com.anandm.composeview.network.data.PokemonDTO
import com.anandm.composeview.network.data.PokemonViewDTO
import com.anandm.composeview.network.mapper.PokemonDomainMapper
import javax.inject.Inject

private const val LIMIT = 10
private const val OFFSET = 0

class RemoteRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val pokemonDomainMapper: PokemonDomainMapper
) : RemoteRepository {

    override suspend fun getPokes(): List<PokemonViewDTO> {
        val result = getPokemonList()
        return mapPokemonList(result)
    }

    private suspend fun getPokemonList(): List<PokemonDTO> {
        val response = apiService.getPokeList(LIMIT, OFFSET)
        if (response.isSuccessful) {
            response.body()?.let { body -> return body.results } ?: run { return emptyList() }
        }
        return emptyList()
    }

    private fun mapPokemonList(result: List<PokemonDTO>): List<PokemonViewDTO> {
        return result.map { pokemonDomainMapper.map(it) }
    }
}