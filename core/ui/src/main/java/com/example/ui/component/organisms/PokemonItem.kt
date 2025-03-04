package com.example.ui.component.organisms

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basecomposeproject.core.design.theme.ui.BaseComposeProjectTheme
import com.example.model.Pokemon
import com.example.ui.component.atoms.SwitchIconButton

// TODO: 画像表示実装
@Composable
fun PokemonItem(
    pokemon: Pokemon,
    modifier: Modifier = Modifier,
    onFavoriteClick: (String) -> Unit,
) = Card(modifier = modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp)
            .padding(bottom = 12.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = pokemon.name)
            Spacer(modifier = Modifier.weight(1f))
            SwitchIconButton(
                iconVectorOn = Icons.Default.Favorite,
                iconVectorOff = Icons.Default.FavoriteBorder,
                isOn = pokemon.isFavorite,
                onClick = { onFavoriteClick(pokemon.name) },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PokemonItemPreview() = BaseComposeProjectTheme {
    PokemonItem(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        pokemon = Pokemon(
            name = "ピカチュウ",
            url = "https://placehold.jp/150x150.png",
            isFavorite = false,
        ),
        onFavoriteClick = {},
    )
}
