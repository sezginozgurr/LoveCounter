package com.example.lovecounter.presentation.memories

import com.example.lovecounter.data.model.Memory

object MemoriesContract {
    data class UiState(
        val memories: List<Memory> = emptyList(),
        val isLoading: Boolean = true,
    )

    sealed interface UiAction {
        data object OnAddMemoryClick : UiAction
        data class OnMemoryClick(val memory: Memory) : UiAction
    }
}
