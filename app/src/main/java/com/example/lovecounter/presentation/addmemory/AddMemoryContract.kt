package com.example.lovecounter.presentation.addmemory

object AddMemoryContract {
    data class UiState(
        val selectedCategory: EventCategory? = null,
        val selectedDate: Long = System.currentTimeMillis(),
        val description: String = "",
        val isSaving: Boolean = false,
    )

    sealed interface UiAction {
        data class OnCategorySelected(val category: EventCategory) : UiAction
        data class OnDateSelected(val date: Long) : UiAction
        data class OnDescriptionChange(val description: String) : UiAction
        data object OnSaveClick : UiAction
    }

    enum class EventCategory(val title: String, val iconIndex: Int) {
        WEDDING_ANNIVERSARY("Evlilik Yıldönümü", 1),
        MEETING_ANNIVERSARY("Tanışma Yıldönümü", 2),
        RELATIONSHIP_ANNIVERSARY("İlişki Yıldönümü", 3),
        OTHER("Diğer", 4)
    }
}
