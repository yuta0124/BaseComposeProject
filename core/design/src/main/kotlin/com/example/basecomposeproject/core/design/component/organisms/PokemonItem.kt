package com.example.basecomposeproject.core.design.component.organisms

import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.basecomposeproject.core.design.theme.ui.BaseComposeProjectTheme

@Composable
fun PokemonItem(
    text: String,
    modifier: Modifier = Modifier,
) = Card(modifier = modifier) {
    Text(text = text)
}

@Preview
@Composable
private fun PokemonItemPreview() = BaseComposeProjectTheme {
    PokemonItem("item")
}
