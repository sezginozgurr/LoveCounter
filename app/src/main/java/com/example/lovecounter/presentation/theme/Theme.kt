package com.example.lovecounter.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

object LCTheme {
    val icons: LCIcons
        @Composable
        @ReadOnlyComposable
        get() = LocalIcons.current
}

@Composable
fun LCTheme(content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalIcons provides LCTheme.icons,
    ) {
        MaterialTheme(
            colorScheme = if (isSystemInDarkTheme()) DarkColors else LightColors,
            typography = Typography,
        ) {
            content()
        }
    }
}