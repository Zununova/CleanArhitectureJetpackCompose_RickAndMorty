package com.example.cleanarhitecturejetpackcompose.data.repositories

import android.util.Log
import com.example.cleanarhitecturejetpackcompose.data.models.toDomain
import com.example.cleanarhitecturejetpackcompose.data.remote.apiservices.CharacterApiService
import com.example.cleanarhitecturejetpackcompose.domain.either.Either
import com.example.cleanarhitecturejetpackcompose.domain.repositories.CharactersRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val characterApiService: CharacterApiService
) : CharactersRepository {

    override fun fetchCharacters() = flow {
        try {
            emit(Either.Success(characterApiService.fetchCharacters().results.map {
                it.toDomain()
            }))
        } catch (exception: Exception) {
            emit(Either.Error(message = exception.message.toString()))
        }
    }

    override fun filterCharacterByName(name: String) = flow {
        try {
            emit(Either.Success(characterApiService.filterCharacterByName(name).results.map {
                it.toDomain()
            }))
        } catch (exception: Exception) {
            emit(Either.Error(exception.message.toString()))
        }
    }
}