@file:OptIn(ExperimentalMaterial3Api::class)

package com.anandmali.composemvvm.pokelist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.anandmali.composemvvm.R
import com.anandmali.composemvvm.ui.theme.ComposeMVVMTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonApp() {
    ComposeMVVMTheme {
        val navController = rememberNavController()
        val pokemonViewModel: PokemonViewModel = viewModel()

        NavHost(navController = navController, startDestination = "PokeList") {
            composable("PokeList") { PokemonList(navController, pokemonViewModel) }
            composable("PokeDetails") { DetailsText() }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun PokemonList(
    navController: NavHostController,
    pokemonViewModel: PokemonViewModel
) {

    val pokeList = pokemonViewModel.pokemonListStatus.value

    Surface(color = MaterialTheme.colorScheme.background) {
        Scaffold(
            topBar = { TopBar() },
            content = { innerPadding ->
                LazyColumn(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    contentPadding = innerPadding
                ) {

                    val sortedList = pokeList.sortedBy {
                        it.name
                    }

                    items(sortedList) { pokemonData ->
                        PokemonListItem(name = pokemonData.name, pokemonData.imageUrl) {
                            navController.navigate("PokeDetails")
                        }
                    }
                }
            }
        )
    }

}

@ExperimentalMaterial3Api
@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = "Pokemon") }
    )
}

@Composable
fun PokemonListItem(
    name: String,
    imageUrl: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .height(140.dp)
            .clipToBounds()
    ) {
        Card(
            shape = MaterialTheme.shapes.medium,
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .align(Alignment.BottomEnd),
        ) {
            Text(
                text = name,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(20.dp),
                style = MaterialTheme.typography.headlineMedium
            )
        }

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .build(),
            error = painterResource(R.drawable.baseline_catching_pokemon_24),
            contentDescription = stringResource(R.string.description),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(130.dp)
                .clip(CircleShape)
                .padding(8.dp)
        )
    }
}

@Composable
fun DetailsText() {
    Surface(color = MaterialTheme.colorScheme.background) {
        Text(text = "This is details screen")
    }
}

@Preview(name = "List Item")
@Composable
private fun ListItemPreview() {
    PokemonListItem(
        name = "Pokemon",
        imageUrl = ""
    ) {}
}
