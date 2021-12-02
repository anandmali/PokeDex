package com.anandm.composeview.network.data

data class PokemonDomainDTO(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<PokemonDTO>
)

data class PokemonDTO(
    val name: String,
    val url: String
)

data class PokemonViewDTO(
    val name: String,
    val url: String,
    val image_url: String
)