package com.example.basecomposeproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.basecomposeproject.core.design.theme.ui.BaseComposeProjectTheme
import com.example.basecomposeproject.feature.search.Search
import com.example.basecomposeproject.feature.search.searchScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BaseComposeApp(modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
fun BaseComposeApp(modifier: Modifier = Modifier) = BaseComposeProjectTheme {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        BaseComposeNavHost()
    }
}

@Composable
fun BaseComposeNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = SearchGraph,
    ) {
        searchGraph(Search)
    }
}

@Serializable
data object SearchGraph

fun NavGraphBuilder.searchGraph(
    startDestination: Any,
) = navigation<SearchGraph>(
    startDestination = startDestination,
) {
    searchScreen()
}
