package com.example.lovecounter.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lovecounter.presentation.theme.LCTheme
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun LCCalendar(
    modifier: Modifier = Modifier,
    initialDate: Long,
    onDateSelected: (Long) -> Unit,
) {
    var currentCalendar by remember { mutableStateOf(Calendar.getInstance()) }
    var selectedDate by remember {
        mutableStateOf<Calendar?>(Calendar.getInstance().apply { timeInMillis = initialDate })
    }
    val daysInMonth = currentCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    val firstDayCalendar =
        (currentCalendar.clone() as Calendar).apply { set(Calendar.DAY_OF_MONTH, 1) }
    val firstDayOfWeek = (firstDayCalendar.get(Calendar.DAY_OF_WEEK) + 5) % 7
    val days = mutableListOf<Int?>()

    repeat(firstDayOfWeek) { days.add(null) }
    repeat(daysInMonth) { days.add(it + 1) }

    LaunchedEffect(initialDate) {
        val newCalendar = Calendar.getInstance().apply { timeInMillis = initialDate }
        currentCalendar = newCalendar
        selectedDate = newCalendar.copy { }
    }

    Column(
        modifier = modifier
            .heightIn(max = 500.dp)
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
    ) {
        HeaderContent(
            currentCalendar = currentCalendar,
            onPreviousMonth = {
                currentCalendar = currentCalendar.copy { add(Calendar.MONTH, -1) }
            },
            onNextMonth = { currentCalendar = currentCalendar.copy { add(Calendar.MONTH, 1) } }
        )
        LCSpacer(14)
        DayNamesContent()
        LCSpacer(14)
        DaysContent(
            currentCalendar = currentCalendar,
            selectedDate = selectedDate,
            days = days,
            onDateSelected = { date ->
                selectedDate = date
                onDateSelected(date.timeInMillis)
            }
        )
    }
}

@Composable
private fun HeaderContent(
    currentCalendar: Calendar,
    onPreviousMonth: () -> Unit,
    onNextMonth: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        IconButton(
            modifier = Modifier.size(24.dp),
            onClick = onPreviousMonth,
        ) {
            Icon(
                imageVector = Icons.Default.ChevronLeft,
                contentDescription = "Previous month",
                tint = MaterialTheme.colorScheme.primary,
            )
        }
        LCSpacer(12)
        Text(
            text = SimpleDateFormat("MMMM yyyy", Locale.getDefault()).format(currentCalendar.time),
            color = MaterialTheme.colorScheme.primary,
            fontSize = 18.sp,
            style = TextStyle(
                fontWeight = FontWeight.SemiBold,
            ),
        )
        LCSpacer(12)
        IconButton(
            modifier = Modifier.size(24.dp),
            onClick = onNextMonth,
        ) {
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Next month",
                tint = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

@Composable
private fun DayNamesContent() {
    Row(modifier = Modifier.fillMaxWidth()) {
        val daysOfWeek = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
        daysOfWeek.forEach { day ->
            Text(
                modifier = Modifier.weight(1f),
                text = day,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
            )
        }
    }
}

@Composable
private fun DaysContent(
    currentCalendar: Calendar,
    selectedDate: Calendar?,
    days: List<Int?> = emptyList(),
    onDateSelected: (Calendar) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        verticalArrangement = Arrangement.spacedBy(6.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        items(days) { day ->
            if (day != null) {
                val dayCalendar = currentCalendar.copy { set(Calendar.DAY_OF_MONTH, day) }
                val isSelected = selectedDate?.let { selected ->
                    selected.get(Calendar.YEAR) == dayCalendar.get(Calendar.YEAR) &&
                        selected.get(Calendar.MONTH) == dayCalendar.get(Calendar.MONTH) &&
                        selected.get(Calendar.DAY_OF_MONTH) == dayCalendar.get(Calendar.DAY_OF_MONTH)
                } == true

                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent)
                        .clickable { onDateSelected(dayCalendar) },
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = day.toString(),
                        color = if (isSelected) Color.White else MaterialTheme.colorScheme.primary,
                        style = TextStyle(
                            fontWeight = FontWeight.SemiBold,
                        ),
                    )
                }
            } else {
                LCSpacer(32)
            }
        }
    }
}

private fun Calendar.copy(invoke: Calendar.() -> Unit): Calendar {
    return (this.clone() as Calendar).apply(invoke)
}

@Preview(showBackground = true)
@Composable
private fun LCCalendarPreview() {
    LCTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            LCCalendar(
                modifier = Modifier.padding(16.dp),
                initialDate = System.currentTimeMillis(),
                onDateSelected = {
                    println(
                        "Selected date: ${
                            SimpleDateFormat(
                                "dd/MM/yyyy",
                                Locale.getDefault()
                            ).format(it)
                        }"
                    )
                }
            )
        }
    }
}
