package com.example.lovecounter.presentation.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.lovecounter.R

sealed class BottomNavItem(
    val route: Screen,
    val iconResId: Int,
    val title: String
) {
    data object Love : BottomNavItem(
        route = Screen.Home,
        iconResId = R.drawable.tab_1,
        title = "Aşk"
    )

    data object Moments : BottomNavItem(
        route = Screen.Memories,
        iconResId = R.drawable.tab_2,
        title = "Anlar"
    )

    data object Gallery : BottomNavItem(
        route = Screen.Profile,
        iconResId = R.drawable.tab_3,
        title = "Galeri"
    )

    data object Settings : BottomNavItem(
        route = Screen.Settings,
        iconResId = R.drawable.tab_4,
        title = "Ayarlar"
    )

    companion object {
        val items = listOf(Love, Moments, Gallery, Settings)
    }
} 