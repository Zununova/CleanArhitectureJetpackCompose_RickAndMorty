package com.example.cleanarhitecturejetpackcompose.presentation.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cleanarhitecturejetpackcompose.presentation.screens.CharacterListScreen
import com.example.cleanarhitecturejetpackcompose.presentation.screens.FilterCharacters
import com.example.cleanarhitecturejetpackcompose.presentation.state.UiState
import com.example.cleanarhitecturejetpackcompose.presentation.ui.theme.CleanArhitectureJetpackComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CleanArhitectureJetpackComposeTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.DarkGray)
                ) {
                    val charactersViewModel: CharactersViewModel = hiltViewModel()
                    val charactersList = charactersViewModel.charactersState.collectAsState().value

                    when (charactersList) {
                        is UiState.Error -> {
                            Toast.makeText(
                                this@MainActivity,
                                charactersList.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        is UiState.Loading -> {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .size(60.dp)
                                    .align(Alignment.Center)
                            )
                        }

                        is UiState.Success -> {
                            Column {
                                FilterCharacters {
                                    charactersViewModel.filterCharacterByName(it)
                                }
                                CharacterListScreen(
                                    modifier = Modifier.fillMaxSize(),
                                    charactersList = charactersList.data
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
