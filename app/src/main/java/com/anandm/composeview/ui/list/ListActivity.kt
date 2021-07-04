package com.anandm.composeview.ui.list

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.anandm.composeview.ui.theme.ComposeViewTheme
import com.anandm.composeview.ui.theme.Purple200
import com.anandm.composeview.viewmodel.ListViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ListActivity : AppCompatActivity() {

    @Inject
    lateinit var value: String

    val listViewModel: ListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeViewTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting(value)
                }
            }
        }

        listViewModel.pokemonStatus.observe(this, {
            TODO("Parse response to list")
            Log.d("Response status => ", it?.toString() ?: "")
        })
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