package com.example.lovecounter.delegation.dialogclient

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

    data object HideDialog : DialogClientEffect()
}