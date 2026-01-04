package com.example.lovecounter.delegation.dialogclient

import com.example.lovecounter.delegation.dialogclient.DialogClientEffect.HideDialog
import com.example.lovecounter.delegation.dialogclient.DialogClientEffect.ShowDialog
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

interface DialogClient {
    val dialogClientEffect: Flow<DialogClientEffect>

    suspend fun showDialog(
        title: String? = null,
        description: String? = null,
        positiveText: String? = null,
        negativeText: String? = null,
        onPositiveClick: () -> Unit = {},
        onNegativeClick: () -> Unit = {},
        onDismiss: () -> Unit = {},
        isCancelable: Boolean = false,
        dismissOnAction: Boolean = true,
    )

    suspend fun hideDialog()
}

class DialogClientDelegate internal constructor() : DialogClient {
    private val _dialogClientEffect = Channel<DialogClientEffect>()
    override val dialogClientEffect = _dialogClientEffect.receiveAsFlow()

    override suspend fun showDialog(
        title: String?,
        description: String?,
        positiveText: String?,
        negativeText: String?,
        onPositiveClick: () -> Unit,
        onNegativeClick: () -> Unit,
        onDismiss: () -> Unit,
        isCancelable: Boolean,
        dismissOnAction: Boolean,
    ) {
        emitEffect(
            ShowDialog(
                title = title,
                description = description,
                positiveText = positiveText,
                negativeText = negativeText,
                onPositiveClick = onPositiveClick,
                onNegativeClick = onNegativeClick,
                isCancelable = isCancelable,
                onDismiss = onDismiss,
                dismissOnAction = dismissOnAction,
            ),
        )
    }

    override suspend fun hideDialog() {
        emitEffect(HideDialog)
    }

    private suspend fun emitEffect(effect: DialogClientEffect) {
        _dialogClientEffect.send(effect)
    }
}

fun dialogClient(): DialogClient = DialogClientDelegate()