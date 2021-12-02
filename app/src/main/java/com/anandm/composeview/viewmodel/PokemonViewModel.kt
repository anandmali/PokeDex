package com.anandm.composeview.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anandm.composeview.network.GetPokemonUseCase
import com.anandm.composeview.network.data.PokemonViewDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val getPokesUseCase: GetPokemonUseCase
) : ViewModel() {

    private val _pokemonListStatus: MutableState<List<PokemonViewDTO>> = mutableStateOf(ArrayList())

    val pokemonListStatus: State<List<PokemonViewDTO>>
        get() = _pokemonListStatus

    init {
        getPokeList()
    }

    private fun getPokeList() {
        val res = getPokesUseCase()
        res.onEach {
            _pokemonListStatus.value = it
        }.launchIn(viewModelScope)
    }
}