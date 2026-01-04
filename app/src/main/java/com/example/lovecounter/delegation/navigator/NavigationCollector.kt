package com.example.lovecounter.delegation.navigator

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.lovecounter.common.collectWithLifecycle
import kotlinx.coroutines.flow.Flow

@Composable
fun NavigationClientCollector(navigationEffect: Flow<NavigationClientEffect>) {
    val navController = LocalNavHostController.current
    navigationEffect.collectWithLifecycle {
        when (it) {
            is NavigationClientEffect.NavigateTo -> navController.navigateUsingEffect(it)
            is NavigationClientEffect.NavigateBack -> navController.popBackStack()
            is NavigationClientEffect.NavigateBackWithData -> {
                navController.previousBackStackEntry?.savedStateHandle?.set(it.data.first, it.data.second)
                navController.popBackStack()
            }
        }
    }
}

private fun NavHostController.navigateUsingEffect(effect: NavigationClientEffect.NavigateTo) {
    navigate(effect.route) {
        effect.popup?.let { popUpTo(it) }
    }
}