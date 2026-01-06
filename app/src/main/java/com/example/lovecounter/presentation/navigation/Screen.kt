package com.example.lovecounter.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable
    data object Onboarding : Screen

    @Serializable
    data object Home : Screen

    @Serializable
    data object Memories : Screen

    @Serializable
    data object Profile : Screen

    @Serializable
    data object Settings : Screen

    @Serializable
    data object Splash : Screen

    @Serializable
    data object AddMemory : Screen

    @Serializable
    data object SpecialDays : Screen

    @Serializable
    data object Recommendations : Screen

    @Serializable
    data object DatingStories : Screen
}