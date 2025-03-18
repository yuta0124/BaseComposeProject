package com.example.uicatalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.basecomposeproject.core.design.theme.ui.BaseComposeProjectTheme
import com.example.ui.component.atoms.IconButton
import com.example.ui.component.atoms.SwitchIconButton

@Composable
fun UiCatalog(modifier: Modifier = Modifier) = BaseComposeProjectTheme {
    val scrollState = rememberScrollState()
    var switchIconButtonOn by remember {
        mutableStateOf(true)
    }

    Surface(
        modifier = modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.verticalScroll(scrollState)) {
            Text("Button")
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                CatalogItem(
                    uiName = "IconButton",
                    ui = {
                        IconButton(
                            iconVector = Icons.Default.Favorite,
                            onClick = {},
                        )
                    }
                )
                CatalogItem(
                    uiName = "SwitchIconButton",
                    ui = {
                        SwitchIconButton(
                            iconVectorOn = Icons.Default.Favorite,
                            iconVectorOff = Icons.Default.FavoriteBorder,
                            isOn = switchIconButtonOn,
                            onClick = {
                                switchIconButtonOn = !switchIconButtonOn
                            }
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun CatalogItem(
    uiName: String,
    ui: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) = Column(horizontalAlignment = Alignment.CenterHorizontally) {
    ui()
    Text(uiName)
}