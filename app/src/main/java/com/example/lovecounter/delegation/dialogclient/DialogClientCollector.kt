package com.example.lovecounter.delegation.dialogclient

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.lovecounter.common.collectWithLifecycle
import com.example.lovecounter.delegation.dialogclient.DialogClientEffect.HideDialog
import com.example.lovecounter.delegation.dialogclient.DialogClientEffect.ShowDialog
import kotlinx.coroutines.flow.Flow

@Composable
fun DialogClientCollector(effect: Flow<DialogClientEffect>) {
    var state by remember { mutableStateOf<ShowDialog?>(null) }

    effect.collectWithLifecycle {
        state = when (it) {
            is ShowDialog -> it
            HideDialog -> null
        }
    }

    state?.let { dialog ->
        /*SVDialog(
            title = dialog.title,
            description = dialog.description,
            positiveText = dialog.positiveText,
            negativeText = dialog.negativeText,
            onPositiveClick = {
                dialog.onPositiveClick()
                if (dialog.dismissOnAction) state = null
            },
            onNegativeClick = {
                dialog.onNegativeClick()
                if (dialog.dismissOnAction) state = null
            },
            isCancelable = dialog.isCancelable,
            onDismiss = {
                dialog.onDismiss()
                if (dialog.dismissOnAction) state = null
            },
        )*/
    }
}