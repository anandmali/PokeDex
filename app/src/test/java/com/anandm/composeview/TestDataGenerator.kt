package com.anandm.composeview

import com.anandm.composeview.network.data.PokeData

internal fun mockPokeData(
    name: String = "name",
    url: String = "url"
) = PokeData(
    name,
    url
)