package com.anandmali.composemvvm.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.anandmali.composemvvm.data.Resource
import com.anandmali.composemvvm.data.source.network.PokeApi
import com.anandmali.composemvvm.data.source.network.response.PokeDetailsResponse
import com.anandmali.composemvvm.data.source.network.response.Pokemon
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokeRepositoryImpl @Inject constructor(
    private val pokeApi: PokeApi,
    private val pagingSource: ListPagingSource
) : PokeRepository {

    override fun getPokeList(): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(pageSize = PAGING_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { pagingSource }
        ).flow
    }

    override suspend fun getPokemonInfo(pokemonName: String): Resource<PokeDetailsResponse> {
        val response = try {
            pokeApi.getPokemonInfo(pokemonName)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred.")
        }
        return Resource.Success(response)
    }
}