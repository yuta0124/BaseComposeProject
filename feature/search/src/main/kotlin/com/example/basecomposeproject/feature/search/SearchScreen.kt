package com.example.basecomposeproject.feature.search

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.basecomposeproject.core.design.theme.ui.BaseComposeProjectTheme

@Composable
fun SearchScreen(modifier: Modifier = Modifier) = Column(modifier = modifier) {
    Text("検索画面")
}

@Preview
@Composable
private fun SearchScreenPreview() = BaseComposeProjectTheme {
    SearchScreen()
}
