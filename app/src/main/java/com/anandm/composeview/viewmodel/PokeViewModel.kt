package com.anandm.composeview.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.anandm.composeview.network.NetworkError
import com.anandm.composeview.network.PokeRepository
import com.anandm.composeview.network.data.PokemonData
import com.anandm.composeview.network.data.PokemonList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokeViewModel
@Inject constructor(
    private val repository: PokeRepository
) : ViewModel() {

    private val _pokemonListStatus: MutableState<List<PokemonData>> = mutableStateOf(ArrayList())

    val pokemonListStatus: State<List<PokemonData>>
        get() = _pokemonListStatus

    private var job: Job = Job()

    private val backGroundScope = CoroutineScope(Dispatchers.IO)

    init {
        getPokemonList()
    }

    private fun getPokemonList() {
        job = backGroundScope.launch {
            try {
                repository.getPokemonList().either(::handlerError, ::handleResponse)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    private fun handleResponse(models: PokemonList) {
        _pokemonListStatus.value = models.results
    }

    private fun handlerError(error: NetworkError) {
        Log.e("Handle error", error.message.toString())
    }
}