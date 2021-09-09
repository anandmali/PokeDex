package com.anandm.composeview.network.data

data class PokeList(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<PokeData>
)

data class PokeData(
    val name: String,
    val url: String
)