package com.example.lovecounter.presentation.recommendations

import com.example.lovecounter.data.model.Recommendation

object RecommendationsContract {
    data class UiState(
        val recommendations: List<Recommendation> = emptyList(),
        val isLoading: Boolean = false,
    )

    sealed interface UiAction {
        data object OnAddRecommendationClick : UiAction
        data class OnLikeClick(val recommendation: Recommendation) : UiAction
        data class OnShareClick(val recommendation: Recommendation) : UiAction
        data class OnRecommendationClick(val recommendation: Recommendation) : UiAction
    }
}
