package com.example.lovecounter.presentation.home

object HomeContract {
    data class UiState(
        val relationshipDuration: RelationshipDuration = RelationshipDuration(),
        val isDateSelected: Boolean = false,
        val showDatePicker: Boolean = false,
        val showMaleImagePicker: Boolean = false,
        val showFemaleImagePicker: Boolean = false,
        val maleImage: Int? = null,
        val femaleImage: Int? = null,
    )

    sealed interface UiAction {
        data object OnSelectDateClick : UiAction
        data object OnDismissDatePicker : UiAction
        data class OnDateSelected(val date: Long) : UiAction
        data object OnMaleImageClick : UiAction
        data object OnFemaleImageClick : UiAction
        data object OnDismissMaleImagePicker : UiAction
        data object OnDismissFemaleImagePicker : UiAction
        data class OnMaleImageSelected(val image: Int) : UiAction
        data class OnFemaleImageSelected(val image: Int) : UiAction
    }
}