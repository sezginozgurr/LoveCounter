package com.example.lovecounter.delegation.dialogclient

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.lovecounter.common.collectWithLifecycle
import com.example.lovecounter.delegation.dialogclient.DialogClientEffect.HideDialog
import com.example.lovecounter.delegation.dialogclient.DialogClientEffect.ShowDatePickerDialog
import com.example.lovecounter.delegation.dialogclient.DialogClientEffect.ShowDialog
import com.example.lovecounter.presentation.components.CustomDatePicker
import kotlinx.coroutines.flow.Flow

@Composable
fun DialogClientCollector(effect: Flow<DialogClientEffect>) {
    var state by remember { mutableStateOf<DialogClientEffect?>(null) }

    effect.collectWithLifecycle {
        state = if (it is HideDialog) null else it
    }

    state?.let {
        when (it) {
            is ShowDialog -> {}
            is ShowDatePickerDialog -> {
                CustomDatePicker(
                    onDismiss = {
                        state = null
                    },
                    onDateSelected = { date ->
                        state = null
                        it.onDateSelected(date)
                    }
                )
            }

            else -> Unit
        }
    }
}