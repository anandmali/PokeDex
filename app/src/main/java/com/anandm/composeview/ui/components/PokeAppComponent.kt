package com.anandm.composeview.ui.components

import android.util.Log
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
import com.anandm.composeview.R
import com.anandm.composeview.network.data.PokemonData
import com.anandm.composeview.ui.theme.ComposeViewTheme
import com.anandm.composeview.ui.theme.Purple200
import com.anandm.composeview.ui.theme.Purple700

@Composable
fun PokeApp(pokeList: List<PokemonData>) {
    ComposeViewTheme {
        Surface(color = MaterialTheme.colors.background) {
            Greeting(pokeList)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Greeting(pokeList: List<PokemonData>) {
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
                GreetCard(pokeData.name)
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
fun GreetCard(name: String) {
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
        text = "Hello ${name.capitalize()}",
        color = Purple200,
        modifier = modifier
            .padding(start = 8.dp)
            .clickable {
                Log.d("Clicked text => ", name)
            },
        style = MaterialTheme.typography.body1
    )
}