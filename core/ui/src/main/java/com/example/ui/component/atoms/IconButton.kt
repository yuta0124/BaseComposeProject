package com.example.ui.component.atoms

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.example.basecomposeproject.core.design.theme.ui.BaseComposeProjectTheme

@Composable
fun IconButton(
    iconVector: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) = IconButton(
    modifier = modifier,
    onClick = onClick,
) {
    Icon(
        imageVector = iconVector,
        contentDescription = null,
    )
}

@Preview
@Composable
private fun IconButtonPreview() = BaseComposeProjectTheme {
    IconButton(
        iconVector = Icons.Default.FavoriteBorder,
        onClick = {},
    )
}
