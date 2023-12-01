package com.example.cleanarhitecturejetpackcompose.data.remote

import com.example.cleanarhitecturejetpackcompose.data.remote.apiservices.CharacterApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {

    private val retrofitClient: Retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .client(
            OkHttpClient().newBuilder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .writeTimeout(5000, TimeUnit.SECONDS)
                .readTimeout(5000, TimeUnit.SECONDS)
                .connectTimeout(5000, TimeUnit.SECONDS)
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun provideCharactersApiServer():
            CharacterApiService = retrofitClient.create(CharacterApiService::class.java)
}