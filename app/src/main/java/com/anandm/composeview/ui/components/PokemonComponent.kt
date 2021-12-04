package com.anandm.composeview.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.anandm.composeview.R
import com.anandm.composeview.ui.theme.ComposeViewTheme
import com.anandm.composeview.viewmodel.PokemonViewModel

@Composable
fun PokemonApp() {
    ComposeViewTheme {
        val navController = rememberNavController()
        val pokemonViewModel: PokemonViewModel = viewModel()

        NavHost(navController = navController, startDestination = "PokeList") {
            composable("PokeList") { PokemonList(navController, pokemonViewModel) }
            composable("PokeDetails") { DetailsText() }
        }
    }
}

@Composable
fun PokemonList(
    navController: NavHostController,
    pokemonViewModel: PokemonViewModel
) {

    val pokeList = pokemonViewModel.pokemonListStatus.value

    Surface(color = MaterialTheme.colors.background) {
        Scaffold(
            topBar = { TopBar() }
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(top = 8.dp, bottom = 8.dp)
            ) {

                val sortedList = pokeList.sortedBy {
                    it.name
                }

                items(sortedList) { pokemonData ->
                    PokemonListItem(name = pokemonData.name, pokemonData.image_url) {
                        navController.navigate("PokeDetails")
                    }
                }
            }
        }
    }
}

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
    Card(
        shape = MaterialTheme.shapes.medium,
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(bottom = 8.dp),
    ) {
        Row(
            modifier = Modifier
                .clickable {
                    onClick()
                },
            verticalAlignment = Alignment.Bottom
        ) {
            ProfileImage(imageUrl)
            ProfileName(name)
        }
    }
}

@Composable
fun ProfileImage(imageUrl: String) {
    Image(
        painter = rememberImagePainter(data = imageUrl, builder = {
            placeholder(R.drawable.ic_launcher_background)
            scale(Scale.FIT)
        }),
        contentDescription = "Profile image",
        modifier = Modifier
            .height(100.dp)
            .width(100.dp)
            .fillMaxHeight()
            .fillMaxWidth(),
        contentScale = ContentScale.Fit
    )
}

@Composable
fun ProfileName(name: String) {
    Text(
        text = name,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp),
        style = MaterialTheme.typography.h4,
        textAlign = TextAlign.Justify
    )
}

@Composable
fun DetailsText() {
    Surface(color = MaterialTheme.colors.background) {
        Text(text = "This is details screen")
    }
}

@Preview(name = "Profile Image")
@Composable
fun ProfileImagePreview() {
    Surface {
        ProfileImage(
            imageUrl = ""
        )
    }
}

@Preview(name = "Profile Name")
@Composable
fun ProfileNamePreview() {
    Surface {
        ProfileName(name = "Profile Name")
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
