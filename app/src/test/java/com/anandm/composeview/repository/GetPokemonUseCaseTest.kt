package com.anandm.composeview.repository

import com.anandm.composeview.mockPokeViewData
import com.anandm.composeview.network.GetPokemonUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetPokemonUseCaseTest {

    private val fakePokeRepository = FakePokemonRepository()
    private val getPokesUseCase = GetPokemonUseCase(fakePokeRepository)

    @Test
    fun `should get list of pokemon correctly`() {
        runBlocking {
            val actual = getPokesUseCase().toList().first()
            val expected = listOf(mockPokeViewData())
            assertEquals(expected, actual)
        }
    }
}