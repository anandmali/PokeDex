package com.anandm.composeview.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.anandm.composeview.network.MutableStatus
import com.anandm.composeview.network.NetworkError
import com.anandm.composeview.network.PokeRepository
import com.anandm.composeview.network.data.PokemonList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class PokeViewModel
@Inject constructor(
    private val repository: PokeRepository
) : ViewModel() {

    val pokemonStatus = MutableStatus<PokemonList>()

    private var job: Job = Job()

    private val backGroundScope = CoroutineScope(Dispatchers.IO)

    init {
//        getPokemonList()
    }

    fun getPokemonList() {
        pokemonStatus postInFlight true
        job = backGroundScope.launch {
            try {
                repository.getPokemonList().either(::handlerError, ::handleResponse)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    private fun handleResponse(models: PokemonList) {
        Log.e("Getpokelist response", models.toString())
        pokemonStatus postSuccess models
    }

    private fun handlerError(error: NetworkError) {
        error.message?.let {
            pokemonStatus postFailure it
        } ?: pokemonStatus postFailure error.messageId
    }

}