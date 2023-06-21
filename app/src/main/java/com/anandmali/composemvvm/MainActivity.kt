package com.anandmali.composemvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.anandmali.composemvvm.pokedetails.DetailsScreen
import com.anandmali.composemvvm.pokelist.ListScreen
import com.anandmali.composemvvm.ui.theme.ComposeMVVMTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeMVVMTheme {

                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "pokeList") {

                    composable("PokeList") {
                        ListScreen(navController)
                    }

                    composable(
                        "pokeDetails/{pokeName}",
                        arguments = listOf(
                            navArgument("pokeName") {
                                type = NavType.StringType
                            }
                        )
                    ) {
                        val pokemonName = remember {
                            it.arguments?.getString("pokeName")
                        }
                        DetailsScreen(
                            pokemonName = pokemonName?.toLowerCase(Locale.ROOT) ?: "",
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}