package com.anandm.composeview

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.anandm.composeview.ui.components.PokemonListItem
import org.junit.Rule
import org.junit.Test

class ListItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val name = "Beedrill"
    private val imageUrl =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${1}.png"


    @Test
    fun `should_render_pokemon_item`(): Unit = with(composeTestRule) {

        composeTestRule.setContent {
            PokemonListItem(
                name = name,
                imageUrl = imageUrl
            ) {}
        }

        onNodeWithText(name, useUnmergedTree = true).assertIsDisplayed()

    }
}

