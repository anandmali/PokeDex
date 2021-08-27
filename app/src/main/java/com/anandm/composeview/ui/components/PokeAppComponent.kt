package com.anandm.composeview.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anandm.composeview.R
import com.anandm.composeview.ui.theme.ComposeViewTheme
import com.anandm.composeview.ui.theme.Purple200
import com.anandm.composeview.ui.theme.Purple700

@Composable
fun PokeApp(name: String) {
    ComposeViewTheme {
        Surface(color = MaterialTheme.colors.background) {
            Greeting(name = name)
        }
    }
}

@Composable
fun Greeting(name: String) {
    Column(
        modifier = Modifier.padding(all = 16.dp),
    ) {
        for (i in 1..5) {
            GreetCard("$name $i")
            Spacer(modifier = Modifier.padding(bottom = 16.dp))
        }
    }
}

@Composable
fun GreetCard(name: String) {
    Card(
        shape = MaterialTheme.shapes.medium,
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp)),
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
        text = "Hello $name",
        color = Purple200,
        modifier = modifier
            .padding(start = 8.dp)
            .clickable {
                Log.d("Clicked text => ", name)
            },
        style = MaterialTheme.typography.body1
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeViewTheme {
        Greeting("Android")
    }
}