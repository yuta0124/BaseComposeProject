package com.example.ui.component.atoms

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.example.basecomposeproject.core.design.theme.ui.BaseComposeProjectTheme

@Composable
fun SwitchIconButton(
    iconVectorOn: ImageVector,
    iconVectorOff: ImageVector,
    isOn: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) = IconButton(
    modifier = modifier,
    onClick = onClick,
) {
    Icon(
        imageVector = if (isOn) iconVectorOn else iconVectorOff,
        contentDescription = null,
    )
}

@Preview(showBackground = true)
@Composable
private fun SwitchIconButtonWhenOnPreview() = BaseComposeProjectTheme {
    SwitchIconButton(
        iconVectorOn = Icons.Default.Favorite,
        iconVectorOff = Icons.Default.FavoriteBorder,
        isOn = true,
        onClick = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun SwitchIconButtonWhenOffPreview() = BaseComposeProjectTheme {
    SwitchIconButton(
        iconVectorOn = Icons.Default.Favorite,
        iconVectorOff = Icons.Default.FavoriteBorder,
        isOn = false,
        onClick = {}
    )
}
