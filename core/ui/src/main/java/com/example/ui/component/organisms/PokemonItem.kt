package com.example.ui.component.organisms

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import coil3.compose.AsyncImage
import com.example.basecomposeproject.core.design.theme.ui.BaseComposeProjectTheme
import com.example.model.Pokemon
import com.example.ui.component.atoms.SwitchIconButton

// TODO: 画像表示実装
@Composable
fun PokemonItem(
    pokemon: Pokemon,
    modifier: Modifier = Modifier,
    onFavoriteClick: (Pokemon) -> Unit,
) = Card(modifier = modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp)
            .padding(bottom = 12.dp),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize(),
                model = pokemon.url,
                contentDescription = null,
            )
            SwitchIconButton(
                modifier = Modifier.align(Alignment.TopEnd),
                iconVectorOn = Icons.Default.Favorite,
                iconVectorOff = Icons.Default.FavoriteBorder,
                isOn = pokemon.isFavorite,
                onClick = { onFavoriteClick(pokemon) },
            )
            Text(
                modifier = Modifier.align(Alignment.BottomCenter),
                text = pokemon.name,
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
