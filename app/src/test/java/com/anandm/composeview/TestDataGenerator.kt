package com.anandm.composeview

import com.anandm.composeview.network.data.PokemonData

internal fun mockPokeData(
    name: String = "name",
    url: String = "url"
) = PokemonData(
    name,
    url
)