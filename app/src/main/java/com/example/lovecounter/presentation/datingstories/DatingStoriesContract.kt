package com.example.lovecounter.presentation.datingstories

import com.example.lovecounter.data.model.DatingStoryEntity

object DatingStoriesContract {
    data class UiState(
        val stories: List<DatingStoryEntity> = emptyList(),
        val isLoading: Boolean = false,
    )

    sealed interface UiAction {
        data object OnAddStoryClick : UiAction
        data class OnStoryClick(val story: DatingStoryEntity) : UiAction
    }
}
