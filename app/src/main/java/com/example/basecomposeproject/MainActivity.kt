package com.example.basecomposeproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.basecomposeproject.core.design.theme.ui.BaseComposeProjectTheme
import com.example.basecomposeproject.feature.search.SearchScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BaseComposeProjectTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    // TODO: Navigation Component実装
                    SearchScreen()
                }
            }
        }
    }
}
