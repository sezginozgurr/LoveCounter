package com.example.lovecounter.presentation.specialday

import androidx.annotation.DrawableRes

object SpecialDayContract {
    data class UiState(
        val events: List<Event> = emptyList(),
        val isLoading: Boolean = false,
    )

    sealed interface UiAction {
        data class OnSpecialDayClick(val type: SpecialDayType) : UiAction
        data class OnEventClick(val event: Event) : UiAction
    }

    enum class SpecialDayType {
        ANNIVERSARY,
        MEETING_ANNIVERSARY,
        BIRTHDAY,
        OTHER
    }
}

data class Event(
    val id: Int,
    @DrawableRes val imageRes: Int,
    val title: String,
    val date: String,
    val time: String,
    @DrawableRes val iconRes: Int,
)
