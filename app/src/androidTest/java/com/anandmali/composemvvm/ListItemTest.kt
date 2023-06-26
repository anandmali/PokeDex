package com.anandmali.composemvvm

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.anandmali.composemvvm.data.source.network.PokemonViewDTO
import com.anandmali.composemvvm.pokelist.PokemonListItem
import org.junit.Rule
import org.junit.Test

class ListItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val name = "Beedrill"
    private val imageUrl =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${1}.png"


    @Test
    fun should_render_pokemon_item(): Unit = with(composeTestRule) {
        composeTestRule.setContent {
            PokemonListItem(
                PokemonViewDTO(
                    5,
                    name = "Pokemon",
                    url = "",
                    imageUrl = ""
                )
            ) {}
        }
        onNodeWithText(name, useUnmergedTree = true).assertIsDisplayed()
    }
}

