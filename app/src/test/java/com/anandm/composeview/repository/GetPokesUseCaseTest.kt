package com.anandm.composeview.repository

import com.anandm.composeview.mockPokeData
import com.anandm.composeview.network.GetPokesUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetPokesUseCaseTest {

    private val fakePokeRepository = FakePokeRepository()
    private val getPokesUseCase = GetPokesUseCase(fakePokeRepository)

    @Test
    fun `should get popular movies correctly`() {
        runBlocking {
            val actual = getPokesUseCase().toList().first()
            val expected = listOf(mockPokeData())
            assertEquals(expected, actual)
        }
    }
}