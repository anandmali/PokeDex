package com.anandm.composeview.ui.list

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.anandm.composeview.network.Status
import com.anandm.composeview.ui.components.DetailsText
import com.anandm.composeview.ui.components.Greeting
import com.anandm.composeview.ui.components.PokeApp
import com.anandm.composeview.viewmodel.PokeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokeListActivity : AppCompatActivity() {

    private val pokeViewModel: PokeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pokeViewModel.pokemonStatus.observe(this) {
            it?.let { status ->
                when (status) {
                    is Status.Error -> Log.d("Error => ", "Error fetching poke list")
                    is Status.IsInFlight -> Log.d("Loading => ", "Loading poke list")
                    is Status.Success -> {
                        setContent {
                            PokeApp(status.data.results)
                        }
                    }
                }
            }
        }
    }
}
