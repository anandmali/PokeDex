package com.anandmali.composemvvm.di

import com.anandmali.composemvvm.BuildConfig
import com.anandmali.composemvvm.data.repository.ListPagingSource
import com.anandmali.composemvvm.data.repository.PokeRepository
import com.anandmali.composemvvm.data.repository.PokeRepositoryImpl
import com.anandmali.composemvvm.data.source.network.PokeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private val baseUrl: String = "https://pokeapi.co/api/v2/"

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply { HttpLoggingInterceptor.Level.BODY }

        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun providePokeApiService(retrofit: Retrofit): PokeApi {
        return retrofit.create(PokeApi::class.java)
    }

    @Singleton
    @Provides
    fun providePokeRepository(
        apiService: PokeApi,
        pagingSource: ListPagingSource
    ): PokeRepository {
        return PokeRepositoryImpl(apiService, pagingSource)
    }

    @Singleton
    @Provides
    fun providePagingSource(apiService: PokeApi): ListPagingSource {
        return ListPagingSource(apiService)
    }

}