package com.anandm.composeview.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.anandm.composeview.ui.theme.ComposeViewTheme
import com.anandm.composeview.ui.theme.Purple200
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
                    .padding(all = 16.dp)
                    .fillMaxWidth(),
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
            .clip(RoundedCornerShape(8.dp))
            .padding(bottom = 16.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(all = 16.dp)
                .clickable {
                    onClick()
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProfileImage(Modifier.align(Alignment.CenterVertically), imageUrl)
            Spacer(modifier = Modifier.padding())
            DefaultText(name)
        }
    }
}

@Composable
fun ProfileImage(modifier: Modifier, imageUrl: String) {
    Image(
        painter = rememberImagePainter(data = imageUrl),
        contentDescription = "Profile image",
        modifier = modifier
            .size(50.dp)
            .clipToBounds()
            .clip(CircleShape)
    )
}

@Preview("Name", showBackground = true)
@Composable
fun DefaultText(
    @PreviewParameter(NamePreviewParameter::class, limit = 1) name: String
) {
    Text(
        text = name.replaceFirstChar { it.uppercase() },
        color = Purple200,
        modifier = Modifier.padding(start = 8.dp, end = 8.dp),
        style = MaterialTheme.typography.body1
    )
}

@Preview("Pokemon list description")
@Composable
fun DetailsText() {
    Surface(color = MaterialTheme.colors.background) {
        Text(text = "This is details screen")
    }
}

@Preview(name = "Course list item")
@Composable
private fun ListItemPreview() {
    PokemonListItem(
        name = "Beedrill",
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${1}.png"
    ) {}
}
