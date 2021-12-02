package com.anandm.composeview

import com.anandm.composeview.network.data.PokemonDTO

internal fun mockPokeData(
    name: String = "name",
    url: String = "url"
) = PokemonDTO(
    name,
    url
)