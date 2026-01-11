package com.example.lovecounter.presentation.recommendations

import com.example.lovecounter.data.model.RecommendationEntity

object RecommendationsContract {
    data class UiState(
        val recommendationEntities: List<RecommendationEntity> = emptyList(),
        val isLoading: Boolean = false,
    )

    sealed interface UiAction {
        data object OnAddRecommendationClick : UiAction
        data class OnLikeClick(val recommendationEntity: RecommendationEntity) : UiAction
        data class OnShareClick(val recommendationEntity: RecommendationEntity) : UiAction
        data class OnRecommendationClick(val recommendationEntity: RecommendationEntity) : UiAction
    }
}
