package com.anandm.composeview

import com.anandm.composeview.network.data.PokemonViewDTO

internal fun mockPokeViewData(
    name: String = "Bulbasaur",
    url: String = "https://pokeapi.co/api/v2/pokemon/1/",
    link: String = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
) = PokemonViewDTO(
    name,
    url,
    link
)