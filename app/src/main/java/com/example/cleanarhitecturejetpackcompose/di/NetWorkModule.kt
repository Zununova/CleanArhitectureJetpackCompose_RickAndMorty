package com.example.cleanarhitecturejetpackcompose.di

import com.example.cleanarhitecturejetpackcompose.data.remote.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetWorkModule {

    @Singleton
    @Provides
    fun retrofit() = RetrofitClient()

    @Singleton
    @Provides
    fun provideCharacterApiService(retrofitClient: RetrofitClient) = retrofitClient.provideCharactersApiServer()
}