package com.example.lovecounter.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {
    val route: String

    @Serializable
    data object Onboarding : Screen {
        override val route = "onboarding"
    }

    @Serializable
    data object Login : Screen {
        override val route = "login"
    }

    @Serializable
    data object Register : Screen {
        override val route = "register"
    }

    @Serializable
    data object Home : Screen {
        override val route = "home"
    }

    @Serializable
    data object Memories : Screen {
        override val route = "memories"
    }

    @Serializable
    data object Profile : Screen {
        override val route = "profile"
    }

    @Serializable
    data object Settings : Screen {
        override val route = "settings"
    }

    @Serializable
    data object Splash : Screen {
        override val route = "splash"
    }
}