package com.anandmali.composemvvm.data.source.network

data class PokemonViewDTO(
    val name: String,
    val url: String,
    val image_url: String
)

fun Pokemon.toViewData(): PokemonViewDTO {
    return with(this) {
        PokemonViewDTO(
            name = this.name.replaceFirstChar { it.uppercase() },
            url = this.url,
            image_url = createImageUrl(this.url)
        )
    }
}

private fun createImageUrl(url: String): String {
    val index = url.split("/".toRegex()).dropLast(1).last()
    return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$index.png"
}
