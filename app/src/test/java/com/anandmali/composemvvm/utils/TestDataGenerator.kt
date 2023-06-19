package com.anandmali.composemvvm.utils

import com.anandmali.composemvvm.data.source.network.PokemonViewDTO

internal fun mockPokeViewData(
    name: String = "Bulbasaur",
    url: String = "https://pokeapi.co/api/v2/pokemon/1/",
    link: String = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
) = PokemonViewDTO(
    name,
    url,
    link
)