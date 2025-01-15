package com.example.lovecounter.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.People
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: Screen,
    val icon: ImageVector,
    val title: String
) {
    data object Love : BottomNavItem(
        route = Screen.Home,
        icon = Icons.Default.Favorite,
        title = "Aşk"
    )

    data object Moments : BottomNavItem(
        route = Screen.Memories,
        icon = Icons.Outlined.People,
        title = "Anlar"
    )

    data object Gallery : BottomNavItem(
        route = Screen.Profile,
        icon = Icons.Default.PhotoLibrary,
        title = "Galeri"
    )

    data object Notes : BottomNavItem(
        route = Screen.Settings,
        icon = Icons.Outlined.Create,
        title = "Notlar"
    )

    companion object {
        val items = listOf(Love, Moments, Gallery, Notes)
    }
} 