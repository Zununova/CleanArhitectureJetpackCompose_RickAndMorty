package com.example.cleanarhitecturejetpackcompose.presentation.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.cleanarhitecturejetpackcompose.R
import com.example.cleanarhitecturejetpackcompose.domain.models.Result

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterCharacters( onSearch: (name: String) -> Unit) {
    Box(modifier = Modifier.fillMaxWidth()) {
        var stateFilter by remember {
            mutableStateOf("")
        }
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            value = stateFilter,
            onValueChange = { stateFilter = it },
            label = { Text(text = "Input person name...") },
            colors = TextFieldDefaults.textFieldColors(
                textColor = DarkGray,
                focusedLabelColor = White,
                unfocusedLabelColor = LightGray,
                focusedIndicatorColor = Gray,
                unfocusedIndicatorColor = Gray,
                containerColor = Gray
            ),
            trailingIcon = {
                Icon(
                    modifier = Modifier.clickable { onSearch(stateFilter) },
                    imageVector = Icons.Default.Search,
                    contentDescription = "Icon search",
                    tint = DarkGray
                )
            })
    }
}

@Composable
fun CharacterListScreen(
    modifier: Modifier = Modifier,
    charactersList: List<Result>,
) {
    Column(modifier = modifier.padding(start = 18.dp, end = 18.dp, top = 18.dp)) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(20.dp)) {
            item {
                Text(color = White, text = "Characters list: ")
            }
            items(charactersList) {
                CharacterItem(
                    imageVector = it.image,
                    personName = it.name,
                    location = it.location.name,
                    origin = it.origin.name,
                    status = it.status,
                    species = it.species
                )
            }
        }
    }
}

@Composable
fun CharacterItem(
    modifier: Modifier = Modifier,
    imageVector: String,
    personName: String,
    location: String,
    origin: String,
    status: String,
    species: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Gray)
    ) {
        AsyncImage(
            modifier = Modifier.size(120.dp),
            model = ImageRequest.Builder(LocalContext.current).data(imageVector).build(),
            contentDescription = stringResource(R.string.image_discription, personName),
            placeholder = painterResource(id = R.drawable.img),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .padding(start = 20.dp)
                .align(Alignment.CenterVertically),
        ) {
            Text(
                text = personName,
                fontSize = 14.sp,
                color = White
            )
            Text(
                text = stringResource(id = R.string.species_and_status, status, species),
                fontSize = 10.sp,
                color = White
            )
            Text(
                text = stringResource(R.string.last_know_location),
                fontSize = 10.sp,
                color = LightGray
            )
            Text(text = location, fontSize = 14.sp, color = White)
            Text(text = stringResource(R.string.first_seen_in), fontSize = 10.sp, color = LightGray)
            Text(text = origin, fontSize = 14.sp, color = White)
        }
    }
}