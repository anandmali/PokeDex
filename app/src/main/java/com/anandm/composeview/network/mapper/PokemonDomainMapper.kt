package com.anandm.composeview.network.mapper

import com.anandm.composeview.network.data.PokemonDTO
import com.anandm.composeview.network.data.PokemonViewDTO

class PokemonDomainMapper : Mapper<PokemonDTO, PokemonViewDTO> {

    override fun map(domainDTO: PokemonDTO): PokemonViewDTO {
        return with(domainDTO) {
            PokemonViewDTO(
                name = this.name.replaceFirstChar { it.uppercase() },
                url = this.url,
                image_url = createImageUrl(this.url)
            )
        }
    }

    private fun createImageUrl(url: String): String {
        val index = url.split("/".toRegex()).dropLast(1).last()
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$index.png"
    }
}