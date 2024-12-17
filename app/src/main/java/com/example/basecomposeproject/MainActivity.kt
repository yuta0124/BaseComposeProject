package com.example.basecomposeproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.basecomposeproject.core.design.theme.ui.BaseComposeProjectTheme
import com.example.basecomposeproject.feature.search.SearchScreen
import com.example.data.di.RepositoryProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var repositoryProvider: RepositoryProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            repositoryProvider.Provide {
                BaseComposeApp()
            }
        }
    }
}

@Composable
fun BaseComposeApp(modifier: Modifier = Modifier) = BaseComposeProjectTheme {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        // TODO: Navigation
        SearchScreen()
    }
}
