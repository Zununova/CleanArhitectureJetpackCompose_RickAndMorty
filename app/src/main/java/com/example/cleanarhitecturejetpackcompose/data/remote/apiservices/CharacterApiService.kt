package com.example.cleanarhitecturejetpackcompose.data.remote.apiservices

import com.example.cleanarhitecturejetpackcompose.data.models.CharacterResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApiService {

    @GET("character")
    suspend fun fetchCharacters() : CharacterResponseDto

    @GET("character")
    suspend fun filterCharacterByName(
        @Query("name") name: String
    ) : CharacterResponseDto
}