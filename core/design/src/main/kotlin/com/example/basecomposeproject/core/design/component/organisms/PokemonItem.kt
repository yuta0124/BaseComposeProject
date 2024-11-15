package com.example.basecomposeproject.core.design.component.organisms

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basecomposeproject.core.design.theme.ui.BaseComposeProjectTheme
import com.example.model.Pokemon

// TODO: 画像表示実装
@Composable
fun PokemonItem(
    pokemon: Pokemon,
    modifier: Modifier = Modifier,
) = Card(modifier = modifier) {
    Text(text = pokemon.name)
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
        )
    )
}
