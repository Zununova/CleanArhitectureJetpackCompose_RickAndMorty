package com.example.cleanarhitecturejetpackcompose.presentation.activities

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarhitecturejetpackcompose.domain.either.Either
import com.example.cleanarhitecturejetpackcompose.domain.models.Result
import com.example.cleanarhitecturejetpackcompose.domain.repositories.CharactersRepository
import com.example.cleanarhitecturejetpackcompose.presentation.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(private val charactersRepository: CharactersRepository) :
    ViewModel() {

    var charactersState = MutableStateFlow<UiState<List<Result>>>(UiState.Loading())

    init {
        fetchCharacters()
    }

    private fun fetchCharacters() {
        viewModelScope.launch {
            charactersRepository.fetchCharacters().collect {
                when (it) {
                    is Either.Error -> {
                        charactersState.value = UiState.Error(it.message)
                    }

                    is Either.Success -> {
                        charactersState.value = UiState.Success(it.data)
                    }
                }
            }
        }
    }

    fun filterCharacterByName(name: String) {
        viewModelScope.launch {
            charactersRepository.filterCharacterByName(name).collect {
                when (it) {
                    is Either.Error -> {
                        Log.e("filterCharacterById", it.message)
                    }

                    is Either.Success -> {
                        charactersState.value = UiState.Success(it.data)
                    }
                }
            }
        }
    }
}