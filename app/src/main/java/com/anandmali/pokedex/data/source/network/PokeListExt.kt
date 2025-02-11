package com.anandmali.pokedex.data.source.network

import com.anandmali.pokedex.data.source.network.response.Pokemon

data class PokemonViewDTO(
    val id: Int,
    val name: String,
    val url: String,
    val imageUrl: String
)

fun Pokemon.toViewData(): PokemonViewDTO {
    return with(this) {
        val index = createId(this.url)
        PokemonViewDTO(
            id = index,
            name = this.name.replaceFirstChar { it.uppercase() },
            url = this.url,
            imageUrl = createImageUrl(index)
        )
    }
}

fun createImageUrl(index: Int): String {
    return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$index.png"
}

private fun createId(url: String): Int {
    return url.split("/".toRegex()).dropLast(1).last().toInt()
}