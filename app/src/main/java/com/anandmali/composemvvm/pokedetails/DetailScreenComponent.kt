@file:OptIn(ExperimentalMaterial3Api::class)

package com.anandmali.composemvvm.pokedetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.anandmali.composemvvm.data.source.network.createImageUrl
import com.anandmali.composemvvm.data.source.network.response.PokeDetailsResponse
import com.anandmali.composemvvm.data.Resource

@Composable
fun DetailsScreen(
    pokemonName: String,
    navController: NavController,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val pokeDetails = produceState<Resource<PokeDetailsResponse>>(initialValue = Resource.Loading()) {
        value = viewModel.getPokemonInfo(pokemonName)
    }.value


    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                title = {
                    Text(
                        text = pokemonName.replaceFirstChar { it.uppercaseChar() },
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        },
        modifier = Modifier.background(MaterialTheme.colorScheme.onBackground)
    ) { paddingValues ->
        PokemonDetailStateWrapper(
            pokemonInfo = pokeDetails,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        )
    }
}


@Composable
fun PokemonDetailStateWrapper(
    pokemonInfo: Resource<PokeDetailsResponse>,
    modifier: Modifier = Modifier
) {
    when (pokemonInfo) {
        is Resource.Success -> {
            PokemonDetailSection(
                pokeDetails = pokemonInfo.data!!,
                modifier = modifier
            )
        }

        is Resource.Error -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = pokemonInfo.message!!,
                    color = Color.Red,
                    modifier = modifier
                )
            }
        }

        is Resource.Loading -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.secondary,
                )
            }
        }
    }
}

@Composable
fun PokemonDetailSection(
    pokeDetails: PokeDetailsResponse,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(createImageUrl(pokeDetails.id))
                .build(),
            contentDescription = pokeDetails.name,
            contentScale = ContentScale.Fit,
            modifier = Modifier.aspectRatio(1.2f)
        )

        PokemonType(types = pokeDetails.types)

        PokemonSize(
            pokeWeight = pokeDetails.weight,
            pokeHeight = pokeDetails.height
        )
        PokemonBaseStats(
            pokeDetails = pokeDetails,
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth(.9f)
        )
    }
}