package com.example.lovecounter.delegation.dialogclient

import java.util.Date

sealed class DialogClientEffect {
    data class ShowDialog(
        val title: String?,
        val description: String?,
        val isCancelable: Boolean,
        val dismissOnAction: Boolean,
        val positiveText: String?,
        val negativeText: String?,
        val onPositiveClick: () -> Unit,
        val onNegativeClick: () -> Unit,
        val onDismiss: () -> Unit,
    ) : DialogClientEffect()

    data class ShowDatePickerDialog(
        val onDateSelected: (Date) -> Unit,
    ) : DialogClientEffect()

    data object HideDialog : DialogClientEffect()
}