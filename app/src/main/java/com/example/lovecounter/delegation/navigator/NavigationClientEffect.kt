package com.example.lovecounter.delegation.navigator

import com.example.lovecounter.presentation.navigation.Screen

sealed interface NavigationClientEffect {
    data class NavigateTo(
        val route: Screen,
        val popup: Screen? = null,
        val restoreState: Boolean = true,
    ) : NavigationClientEffect

    data object NavigateBack : NavigationClientEffect

    data class NavigateBackWithData(val data: Pair<String, Any>) : NavigationClientEffect
}