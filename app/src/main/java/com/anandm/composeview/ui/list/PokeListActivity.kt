package com.anandm.composeview.ui.list

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.anandm.composeview.ui.components.PokeApp
import com.anandm.composeview.viewmodel.PokeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PokeListActivity : AppCompatActivity() {

    @Inject
    lateinit var value: String

    private val pokeViewModel: PokeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokeApp(value)
        }

        pokeViewModel.pokemonStatus.observe(this) {
            Log.d("Response status => ", it?.toString() ?: "")
        }
    }
}
