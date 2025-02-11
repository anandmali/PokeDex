package com.anandmali.pokedex.pokedetails

import androidx.lifecycle.ViewModel
import com.anandmali.pokedex.data.repository.PokeRepository
import com.anandmali.pokedex.data.source.network.response.PokeDetailsResponse
import com.anandmali.pokedex.data.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val remoteRepository: PokeRepository
) : ViewModel() {

    suspend fun getPokemonInfo(pokemonName: String): Resource<PokeDetailsResponse> {
        return remoteRepository.getPokemonInfo(pokemonName)
    }
}