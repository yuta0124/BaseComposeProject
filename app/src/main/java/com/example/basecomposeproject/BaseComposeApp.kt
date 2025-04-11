package com.example.basecomposeproject

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.basecomposeproject.core.design.theme.ui.BaseComposeProjectTheme
import com.example.basecomposeproject.navigation.BaseComposeNavHost

@Composable
fun BaseComposeApp(modifier: Modifier = Modifier) = BaseComposeProjectTheme {
    Surface(modifier = modifier) {
        BaseComposeNavHost()
    }
}
