package com.example.lovecounter.presentation.addmemory

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lovecounter.presentation.components.LCCalendar
import com.example.lovecounter.presentation.components.LCSpacer
import com.example.lovecounter.presentation.theme.LCTheme
import com.example.lovecounter.presentation.theme.LocalIcons

@Composable
fun AddMemoryScreen(
    uiState: AddMemoryContract.UiState,
    onAction: (AddMemoryContract.UiAction) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.secondary,
                        MaterialTheme.colorScheme.tertiary,
                    )
                )
            )
            .verticalScroll(rememberScrollState()),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
                )
                .padding(24.dp),
        ) {
            Text(
                text = "Özel Gün Ekle",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = 36.sp
            )

            LCSpacer(8)

            Text(
                text = "Lütfen bir özel gün seçiniz",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 12.sp
            )

            LCSpacer(8)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                AddMemoryContract.EventCategory.entries.forEach { category ->
                    CategoryItem(
                        category = category,
                        isSelected = uiState.selectedCategory == category,
                        onClick = { onAction(AddMemoryContract.UiAction.OnCategorySelected(category)) }
                    )
                }
            }
        }

        LCSpacer(16)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Lütfen Etkinlik Tarihinizi Giriniz",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.Start)
            )

            LCSpacer(12)

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                LCCalendar(
                    modifier = Modifier.fillMaxWidth(),
                    initialDate = uiState.selectedDate,
                    onDateSelected = { date ->
                        onAction(AddMemoryContract.UiAction.OnDateSelected(date))
                    }
                )
            }

            LCSpacer(24)

            Text(
                text = "Lütfen Açıklama Ekleyiniz",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.Start)
            )

            LCSpacer(12)

            TextField(
                value = uiState.description,
                onValueChange = { onAction(AddMemoryContract.UiAction.OnDescriptionChange(it)) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFFFC4C4),
                    unfocusedContainerColor = Color(0xFFFFC4C4),
                    disabledContainerColor = Color(0xFFFFC4C4),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),
                shape = RoundedCornerShape(16.dp),
                textStyle = TextStyle(fontSize = 14.sp),
                minLines = 3,
                enabled = !uiState.isSaving
            )

            LCSpacer(24)

            Button(
                onClick = { onAction(AddMemoryContract.UiAction.OnSaveClick) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                enabled = !uiState.isSaving && uiState.selectedCategory != null && uiState.description.isNotBlank(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.primary,
                    disabledContentColor = MaterialTheme.colorScheme.primary,
                    disabledContainerColor = MaterialTheme.colorScheme.background,
                )
            ) {
                if (uiState.isSaving) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                } else {
                    Text(
                        text = "Tamam",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
}

@Composable
private fun RowScope.CategoryItem(
    category: AddMemoryContract.EventCategory,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    val icons = LocalIcons.current
    val icon = when (category.iconIndex) {
        1 -> icons.specialDayOne
        2 -> icons.specialDayTwo
        3 -> icons.specialDayThree
        4 -> icons.specialDayFour
        else -> icons.specialDayOne
    }

    Column(
        modifier = Modifier
            .weight(1f)
            .clickable(onClick = onClick)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(if (isSelected) Color.White else Color.White.copy(alpha = 0.7f))
                .border(
                    width = if (isSelected) 3.dp else 0.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape
                )
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = category.title,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
            )
        }
        LCSpacer(8)
        Text(
            text = category.title,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 10.sp,
            textAlign = TextAlign.Center,
            lineHeight = 12.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AddMemoryScreenPreview() {
    LCTheme {
        AddMemoryScreen(
            uiState = AddMemoryContract.UiState(
                selectedCategory = AddMemoryContract.EventCategory.WEDDING_ANNIVERSARY,
                selectedDate = System.currentTimeMillis(),
                description = "",
                isSaving = false
            ),
            onAction = {}
        )
    }
}