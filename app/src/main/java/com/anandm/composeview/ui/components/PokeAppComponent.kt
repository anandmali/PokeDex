package com.anandm.composeview.ui.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.anandm.composeview.ui.theme.ComposeViewTheme
import com.anandm.composeview.ui.theme.Purple200

@Composable
fun PokeApp(value: String) {
    ComposeViewTheme {
        Surface(color = MaterialTheme.colors.background) {
            Greeting(name = value)
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(
        text = "Hello $name!",
        color = Purple200,
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeViewTheme {
        Greeting("Android")
    }
}