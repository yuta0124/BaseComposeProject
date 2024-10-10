package com.example.basecomposeproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.basecomposeproject.core.design.theme.ui.BaseComposeProjectTheme
import com.example.basecomposeproject.feature.search.SearchScreen
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import com.slack.circuitx.android.rememberAndroidScreenAwareNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var circuit: Circuit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BaseComposeProjectTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    CircuitCompositionLocals(circuit = circuit) {
                        val backStack = rememberSaveableBackStack(SearchScreen)
                        val circuitNavigator = rememberCircuitNavigator(backStack)
                        val navigator = rememberAndroidScreenAwareNavigator(circuitNavigator, this)
                        NavigableCircuitContent(navigator, backStack)
                    }
                }
            }
        }
    }
}
