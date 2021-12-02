package com.anandm.composeview.network.data

data class PokemonListData(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<PokemonData>
)

data class PokemonData(
    val name: String,
    val url: String,
    val image_url: String
)