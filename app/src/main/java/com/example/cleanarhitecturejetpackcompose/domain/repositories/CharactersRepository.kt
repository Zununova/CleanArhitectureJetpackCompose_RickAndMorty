package com.example.cleanarhitecturejetpackcompose.domain.repositories

import com.example.cleanarhitecturejetpackcompose.domain.either.Either
import com.example.cleanarhitecturejetpackcompose.domain.models.Result
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

    fun fetchCharacters(): Flow<Either<List<Result>, String>>
    fun filterCharacterByName(name: String): Flow<Either<List<Result>, String>>
}