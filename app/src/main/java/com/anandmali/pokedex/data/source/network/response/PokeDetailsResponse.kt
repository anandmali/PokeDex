package com.anandmali.pokedex.data.source.network.response

data class PokeDetailsResponse(
    val height: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int
)