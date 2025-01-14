package com.example.lovecounter.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {

    @Serializable
    data object Onboarding : Screen

    @Serializable
    data object Login : Screen

    @Serializable
    data object Register : Screen

    @Serializable
    data object Home : Screen


}