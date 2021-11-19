package com.anandm.composeview.network

import com.anandm.composeview.network.data.PokemonData
import com.anandm.composeview.network.data.PokemonListData

interface PokemonListMapper<in T, out R> {
    fun map(domainModel: T): R
}

class PokemonListMapperImpl : PokemonListMapper<PokemonListData, List<PokemonData>> {

    override fun map(pokemonListData: PokemonListData): List<PokemonData> {
        val pokemonList = ArrayList<PokemonData>()

        pokemonListData.results.forEach { domainModel ->

            val pokemonId = if (domainModel.url.endsWith("/")) {
                domainModel.url.dropLast(1).takeLastWhile { it.isDigit() }
            } else {
                domainModel.url.takeLastWhile { it.isDigit() }
            }

            val imageUrl =
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemonId}.png"

            pokemonList.add(PokemonData(domainModel.name, imageUrl))

        }
        return emptyList()
    }

}