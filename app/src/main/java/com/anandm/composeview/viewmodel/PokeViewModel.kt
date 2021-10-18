package com.anandm.composeview.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anandm.composeview.network.GetPokesUseCase
import com.anandm.composeview.network.data.PokeData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PokeViewModel @Inject constructor(
    private val getPokesUseCase: GetPokesUseCase
) : ViewModel() {

    private val _pokeListStatus: MutableState<List<PokeData>> = mutableStateOf(ArrayList())

    val pokeListStatus: State<List<PokeData>>
        get() = _pokeListStatus

    init {
        getPokeList()
    }

    private fun getPokeList() {
        getPokesUseCase().onEach {
            _pokeListStatus.value = it
        }.launchIn(viewModelScope)
    }
}