package com.anandm.composeview.repository

import com.anandm.composeview.mockPokeData
import com.anandm.composeview.network.GetPokemonUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetPokemonUseCaseTest {

    private val fakePokeRepository = FakePokemonRepository()
    private val getPokesUseCase = GetPokemonUseCase(fakePokeRepository)

    @Test
    fun `should get popular movies correctly`() {
        runBlocking {
            val actual = getPokesUseCase().toList().first()
            val expected = listOf(mockPokeData())
            assertEquals(expected, actual)
        }
    }
}