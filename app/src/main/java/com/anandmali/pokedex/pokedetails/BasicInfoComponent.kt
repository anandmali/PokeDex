package com.anandmali.pokedex.pokedetails

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anandmali.pokedex.R
import com.anandmali.pokedex.data.source.network.response.Type

@Composable
fun PokemonType(types: List<Type>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(types) {
            Text(
                text = it.type.name.replaceFirstChar { char -> char.uppercaseChar() },
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .border(1.dp, MaterialTheme.colorScheme.onBackground, CircleShape)
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }
}
@Composable
fun PokemonSize(
    pokeWeight: Int,
    pokeHeight: Int
) {
    val weight = remember { pokeWeight / 10f }
    val height = remember { pokeHeight / 10f }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 16.dp)
    ) {
        PokeSizeData(
            value = weight,
            unit = "kg",
            valueType = stringResource(id = R.string.weight),
            modifier = Modifier.weight(1f)
        )

        PokeSizeData(
            value = height,
            unit = "m",
            valueType = stringResource(id = R.string.height),
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun PokeSizeData(
    value: Float,
    unit: String,
    valueType: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(
            text = "$value$unit",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Text(
            text = valueType,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}
