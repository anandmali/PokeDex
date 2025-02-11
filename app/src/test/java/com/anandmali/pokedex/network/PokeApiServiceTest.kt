package com.anandmali.pokedex.network

import com.anandmali.pokedex.data.repository.PokeRepositoryImpl
import com.anandmali.pokedex.data.source.network.PokeApi
import com.anandmali.pokedex.utils.enqueueResponse
import com.anandmali.pokedex.utils.mockPokeViewData
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokeDetailsRepositoryTest {

    private val mockWebServer = MockWebServer()

    private val okHttpClient = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val pokeApiService = retrofit.create(PokeApi::class.java)

    private val sut = PokeRepositoryImpl(pokeApiService)

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `should pokemon list correctly given 200 response`() {
        mockWebServer.enqueueResponse("pokemon-list.json", 200)
        runBlocking {
            val actual = sut.getPokeList()
            val expected = listOf(mockPokeViewData())
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `should fetch empty list given !200 response`() {
        mockWebServer.enqueueResponse("pokemon-list.json", 400)
        runBlocking {
            val actual = sut.getPokeList()
            assertTrue(actual.isEmpty())
        }
    }
}