package com.anandmali.composemvvm.pokelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.anandmali.composemvvm.data.repository.PokeRepository
import com.anandmali.composemvvm.data.source.network.PokemonViewDTO
import com.anandmali.composemvvm.data.source.network.toViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val pokeRepository: PokeRepository
) : ViewModel() {

    val pokemonListStatus: Flow<PagingData<PokemonViewDTO>>

    init {
        pokemonListStatus = getPokeList().cachedIn(viewModelScope)
    }

    private fun getPokeList(): Flow<PagingData<PokemonViewDTO>> {
        return pokeRepository.getPokeList()
            .map { data ->
                data.map {
                    it.toViewData()
                }
            }
    }
}