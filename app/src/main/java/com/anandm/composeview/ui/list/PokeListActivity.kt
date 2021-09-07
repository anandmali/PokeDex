package com.anandm.composeview.ui.list

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.anandm.composeview.ui.components.PokeApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokeListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokeApp()
        }
    }
}
