package com.example.lovecounter.delegation.navigator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController

@Composable
fun RememberNavController(
    navController: NavHostController,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalNavHostController provides navController,
        content = content,
    )
}

val LocalNavHostController = staticCompositionLocalOf<NavHostController> {
    noLocalProvidedFor("LocalNavHostController")
}

@Suppress("SameParameterValue")
private fun noLocalProvidedFor(name: String): Nothing {
    error("CompositionLocal $name not present")
}