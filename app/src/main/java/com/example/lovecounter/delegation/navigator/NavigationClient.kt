package com.example.lovecounter.delegation.navigator

import com.example.lovecounter.presentation.navigation.Screen
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

interface NavigationClient {
    val navigationClientEffect: Flow<NavigationClientEffect>
    suspend fun navigateTo(route: Screen, popup: Screen? = null, restoreState: Boolean = true)
    suspend fun navigateBack()
    suspend fun navigateBack(data: Pair<String, Any>)
}

class NavigationClientDelegate internal constructor() : NavigationClient {

    private val _navigationClientEffect = Channel<NavigationClientEffect>()
    override val navigationClientEffect: Flow<NavigationClientEffect> = _navigationClientEffect.receiveAsFlow()

    override suspend fun navigateTo(route: Screen, popup: Screen?, restoreState: Boolean) {
        emitEffect(NavigationClientEffect.NavigateTo(route, popup, restoreState))
    }

    override suspend fun navigateBack() {
        emitEffect(NavigationClientEffect.NavigateBack)
    }

    override suspend fun navigateBack(data: Pair<String, Any>) {
        emitEffect(NavigationClientEffect.NavigateBackWithData(data = data))
    }

    private suspend fun emitEffect(effect: NavigationClientEffect) {
        _navigationClientEffect.send(effect)
    }
}

fun navigationClient(): NavigationClient = NavigationClientDelegate()