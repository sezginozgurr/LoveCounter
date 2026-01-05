package com.example.lovecounter.presentation.components

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource

@Composable
fun LCIcon(
    modifier: Modifier = Modifier,
    vector: ImageVector,
    tint: Color = Color.Unspecified,
    contentDescription: String? = null,
) {
    Icon(
        modifier = modifier,
        imageVector = vector,
        tint = tint,
        contentDescription = contentDescription,
    )
}

@Composable
fun LCIcon(
    modifier: Modifier = Modifier,
    resource: Int,
    tint: Color = Color.Unspecified,
    contentDescription: String? = null,
) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = resource),
        tint = tint,
        contentDescription = contentDescription,
    )
}