@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.anandmali.composemvvm.pokelist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.anandmali.composemvvm.R
import com.anandmali.composemvvm.data.source.network.PokemonViewDTO

@Composable
fun ListScreen(
    navController: NavHostController,
    listViewModel: ListViewModel = hiltViewModel()
) {

    val pokeList = listViewModel.pokemonListStatus.value

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
                    items(pokeList) { pokemonData ->
                        PokemonListItem(pokemonData) {
                            navController.navigate("pokeDetails/${pokemonData.name}")
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
    poke: PokemonViewDTO,
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
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(10.dp),
            ) {
                Text(
                    text = "#${poke.id}",
                    style = MaterialTheme.typography.labelLarge
                )

                Text(
                    text = poke.name,
                    modifier = Modifier.fillMaxHeight(),
                    style = MaterialTheme.typography.headlineLarge
                )
            }
        }

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(poke.imageUrl)
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

@Preview(name = "List Item")
@Composable
private fun ListItemPreview() {
    PokemonListItem(
        PokemonViewDTO(
            5,
            name = "Pokemon",
            url = "",
            imageUrl = ""
        )
    ) {}
}
