package com.example.lovecounter.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import android.widget.NumberPicker
import java.util.Date
import com.example.lovecounter.presentation.theme.AppColor
import android.graphics.Paint
import android.widget.TextView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePicker(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onDateSelected: (Date) -> Unit
) {
    if (showDialog) {
        val context = LocalContext.current
        
        var selectedDay by remember { mutableStateOf(1) }
        var selectedMonth by remember { mutableStateOf(0) }
        var selectedYear by remember { mutableStateOf(2024) }
        
        val months = arrayOf(
            "Ocak", "Şubat", "Mart", "Nisan", "Mayıs", "Haziran",
            "Temmuz", "Ağustos", "Eylül", "Ekim", "Kasım", "Aralık"
        )

        ModalBottomSheet(
            onDismissRequest = onDismiss,
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
            dragHandle = null,
            containerColor = Color.White,
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Tarih Seçin",
                    style = MaterialTheme.typography.titleMedium
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // NumberPicker'ların metin rengini siyah yapan fonksiyon
                fun NumberPicker.setTextColor() {
                    val pickerFields = NumberPicker::class.java.declaredFields
                    for (field in pickerFields) {
                        if (field.name == "mSelectorWheelPaint") {
                            field.isAccessible = true
                            (field.get(this) as Paint).apply {
                                color = android.graphics.Color.BLACK
                            }
                        }
                    }
                    
                    descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
                    wrapSelectorWheel = false
                    textColor = android.graphics.Color.BLACK
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Gün Seçici
                    AndroidView(
                        factory = { context ->
                            NumberPicker(context).apply {
                                minValue = 1
                                maxValue = 31
                                value = selectedDay
                                setOnValueChangedListener { _, _, newVal ->
                                    selectedDay = newVal
                                }
                                setTextColor()
                            }
                        },
                        modifier = Modifier.weight(1f)
                    )
                    
                    // Ay Seçici
                    AndroidView(
                        factory = { context ->
                            NumberPicker(context).apply {
                                minValue = 0
                                maxValue = 11
                                displayedValues = months
                                value = selectedMonth
                                setOnValueChangedListener { _, _, newVal ->
                                    selectedMonth = newVal
                                }
                                setTextColor()
                            }
                        },
                        modifier = Modifier.weight(1f)
                    )
                    
                    // Yıl Seçici
                    AndroidView(
                        factory = { context ->
                            NumberPicker(context).apply {
                                minValue = 2000
                                maxValue = 2025
                                value = selectedYear
                                setOnValueChangedListener { _, _, newVal ->
                                    selectedYear = newVal
                                }
                                setTextColor()
                            }
                        },
                        modifier = Modifier.weight(1f)
                    )
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("İptal")
                    }
                    
                    Spacer(modifier = Modifier.width(8.dp))
                    
                    Button(
                        onClick = {
                            val calendar = java.util.Calendar.getInstance()
                            calendar.set(selectedYear, selectedMonth, selectedDay)
                            onDateSelected(calendar.time)
                            onDismiss()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = AppColor)
                    ) {
                        Text("Tamam")
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
} 