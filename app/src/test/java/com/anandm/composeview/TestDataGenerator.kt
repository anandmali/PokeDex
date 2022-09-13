package com.anandm.composeview

import com.anandm.composeview.network.data.PokemonDTO
import com.anandm.composeview.network.data.PokemonViewDTO

internal fun mockPokeData(
    name: String = "name",
    url: String = "url"
) = PokemonDTO(
    name,
    url
)

internal fun mockPokeViewData(
    name: String = "name",
    url: String = "url",
    link: String = "link"
) = PokemonViewDTO(
    name,
    url,
    link
)