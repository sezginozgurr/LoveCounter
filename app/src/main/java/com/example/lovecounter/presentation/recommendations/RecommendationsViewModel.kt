package com.example.lovecounter.presentation.recommendations

import androidx.lifecycle.ViewModel
import com.example.lovecounter.R
import com.example.lovecounter.data.model.Recommendation
import com.example.lovecounter.delegation.dialogclient.DialogClient
import com.example.lovecounter.delegation.dialogclient.dialogClient
import com.example.lovecounter.delegation.mvi.MVI
import com.example.lovecounter.delegation.mvi.mvi
import com.example.lovecounter.delegation.navigator.NavigationClient
import com.example.lovecounter.delegation.navigator.navigationClient
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecommendationsViewModel @Inject constructor() : ViewModel(),
    MVI<RecommendationsContract.UiState, RecommendationsContract.UiAction> by mvi(RecommendationsContract.UiState()),
    NavigationClient by navigationClient(),
    DialogClient by dialogClient() {

    init {
        loadRecommendations()
    }

    override fun onAction(uiAction: RecommendationsContract.UiAction) {
        when (uiAction) {
            is RecommendationsContract.UiAction.OnAddRecommendationClick -> Unit
            is RecommendationsContract.UiAction.OnShareClick -> Unit
            is RecommendationsContract.UiAction.OnRecommendationClick -> Unit
            is RecommendationsContract.UiAction.OnLikeClick -> toggleLike(uiAction.recommendation)
        }
    }

    private fun loadRecommendations() {
        val mockRecommendations = listOf(
            Recommendation(
                id = 1,
                coupleName = "Merve & Özgür",
                duration = "10 Yıl 5 Ay 20 Gün",
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s",
                photoResId = R.drawable.fakephoto
            ),
            Recommendation(
                id = 2,
                coupleName = "Merve & Özgür",
                duration = "10 Yıl 5 Ay 20 Gün",
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s",
                photoResId = R.drawable.fakephoto
            ),
            Recommendation(
                id = 3,
                coupleName = "Merve & Özgür",
                duration = "10 Yıl 5 Ay 20 Gün",
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s",
                photoResId = R.drawable.fakephoto
            )
        )
        updateUiState { copy(recommendations = mockRecommendations) }
    }

    private fun toggleLike(recommendation: Recommendation) {
        updateUiState {
            copy(
                recommendations = recommendations.map {
                    if (it.id == recommendation.id) it.copy(isLiked = !it.isLiked) else it
                }
            )
        }
    }
}
