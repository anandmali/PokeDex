package com.anandm.composeview.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.anandm.composeview.R
import com.anandm.composeview.ui.theme.ComposeViewTheme
import com.anandm.composeview.ui.theme.Purple200
import com.anandm.composeview.ui.theme.Purple700
import com.anandm.composeview.viewmodel.PokeViewModel

@Composable
fun PokeApp() {
    ComposeViewTheme {
        val navController = rememberNavController()
        val pokeViewModel: PokeViewModel = viewModel()

        NavHost(navController = navController, startDestination = "PokeList") {
            composable("PokeList") { Greeting(navController, pokeViewModel) }
            composable("PokeDetails") { DetailsText() }
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Greeting(
    navController: NavHostController,
    pokeViewModel: PokeViewModel
) {

    val pokeList = pokeViewModel.pokemonListStatus.value

    Surface(color = MaterialTheme.colors.background) {
        LazyColumn(
            modifier = Modifier
                .padding(all = 16.dp)
                .fillMaxWidth(),
        ) {
            val groupedPoke = pokeList.groupBy { it.name[0] }

            groupedPoke.forEach { (groupedBy, list) ->
                stickyHeader {
                    CharacterHeader(char = groupedBy, Modifier.fillParentMaxWidth())
                }
                items(list) { pokeData ->
                    GreetCard(pokeData.name, navController)
                }
            }
        }
    }
}

@Composable
fun CharacterHeader(char: Char, modifier: Modifier) {
    Text(
        text = char.toString(),
        color = Purple200,
        modifier = modifier
            .padding(start = 8.dp),
        style = MaterialTheme.typography.body1
    )
}

@Composable
fun GreetCard(name: String, navController: NavHostController) {
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
                    navController.navigate("PokeDetails")
                }
        ) {
            ProfileImage(
                Modifier.align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.padding())
            DefaultText(
                name,
                Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}

@Composable
fun ProfileImage(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "Profile image",
        modifier = modifier
            .size(50.dp)
            .clipToBounds()
            .clip(CircleShape)
            .border(1.5.dp, Purple700, CircleShape)
    )
}

@Composable
fun DefaultText(name: String, modifier: Modifier) {
    Text(
        text = "Hello ${name.replaceFirstChar { it.uppercase() }}",
        color = Purple200,
        modifier = modifier.padding(start = 8.dp),
        style = MaterialTheme.typography.body1
    )
}

@Composable
fun DetailsText() {
    Surface(color = MaterialTheme.colors.background) {
        Text(text = "This is details screen")
    }
}