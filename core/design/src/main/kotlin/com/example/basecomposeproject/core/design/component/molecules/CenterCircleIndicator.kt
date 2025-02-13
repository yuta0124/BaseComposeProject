package com.example.basecomposeproject.core.design.component.molecules

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.basecomposeproject.core.design.theme.ui.BaseComposeProjectTheme

@Composable
fun CenterCircleIndicator(modifier: Modifier = Modifier) = Box(
    modifier = modifier.fillMaxSize(),
    contentAlignment = Alignment.Center,
) {
    CircularProgressIndicator()
}

@Preview(showSystemUi = true)
@Composable
private fun CenterCircleIndicatorPreview() = BaseComposeProjectTheme {
    CenterCircleIndicator()
}
