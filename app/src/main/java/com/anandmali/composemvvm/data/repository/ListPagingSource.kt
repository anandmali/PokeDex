package com.anandmali.composemvvm.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.anandmali.composemvvm.data.source.network.PokeApi
import com.anandmali.composemvvm.data.source.network.response.Pokemon

const val PAGING_SIZE = 10
private const val PAGE_INDEX = 0

class ListPagingSource(
    private val pokeApi: PokeApi
) : PagingSource<Int, Pokemon>() {

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        //Current paging index position
        val position = params.key ?: PAGE_INDEX

        val pokeList = getPokeList(params.loadSize, position)

        //Create next paging index
        val nextKey = if (pokeList.size < PAGING_SIZE) {
            null
        } else {
            position + (params.loadSize / PAGING_SIZE)
        }

        //Create a paging result for PagingData
        return LoadResult.Page(
            data = pokeList,
            prevKey = if (position == 1) null else position - 1,
            nextKey = nextKey
        )
    }

    private suspend fun getPokeList(loadSize: Int, position: Int): List<Pokemon> {
        val response = pokeApi.getPokeList(loadSize, position)
        if (response.isSuccessful) {
            return response.body()?.let { body -> return body.results }
                ?: run { return emptyList() }
        }
        return emptyList()
    }
}