package com.example.lovecounter.presentation.memories

import com.example.lovecounter.data.model.MemoryEntity

object MemoriesContract {
    data class UiState(
        val memories: List<MemoryEntity> = emptyList(),
        val isLoading: Boolean = true,
    )

    sealed interface UiAction {
        data object OnAddMemoryClick : UiAction
        data class OnMemoryClick(val memoryEntity: MemoryEntity) : UiAction
    }
}
