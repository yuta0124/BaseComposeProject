package com.example.basecomposeproject.core.design.component.molecules

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ColumnScope.AppCircularProgressIndicator(
    modifier: Modifier = Modifier,
) {
    Spacer(modifier = Modifier.weight(1f))
    CircularProgressIndicator(modifier = modifier)
    Spacer(modifier = Modifier.weight(1f))
}
