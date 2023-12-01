package com.example.cleanarhitecturejetpackcompose.di

import com.example.cleanarhitecturejetpackcompose.data.repositories.CharactersRepositoryImpl
import com.example.cleanarhitecturejetpackcompose.domain.repositories.CharactersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun provideCharactersRepositories(charactersRepositoryImpl: CharactersRepositoryImpl) : CharactersRepository
}