package com.example.lovecounter.presentation.profile

object ProfileContract {
    data class UiState(
        val userName: String = "",
        val partnerName: String = "",
        val relationshipStartDate: String = "",
        val userPhotoResId: Int? = null,
        val partnerPhotoResId: Int? = null,
        val memoriesCount: Int = 0,
        val specialDaysCount: Int = 0,
    )

    sealed interface UiAction {
        data object OnEditProfileClick : UiAction
        data object OnViewMemoriesClick : UiAction
        data object OnViewSpecialDaysClick : UiAction
        data object OnViewRecommendationsClick : UiAction
        data object OnViewDatingStoriesClick : UiAction
        data object OnSettingsClick : UiAction
    }
}
