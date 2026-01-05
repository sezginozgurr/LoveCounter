package com.example.lovecounter.presentation.specialday

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lovecounter.R
import com.example.lovecounter.presentation.components.LCIcon
import com.example.lovecounter.presentation.theme.LCTheme

@Composable
fun SpecialDaysScreen(
    uiState: SpecialDayContract.UiState,
    onAction: (SpecialDayContract.UiAction) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFDEFEF))
    ) {
        TopSection(onAction = onAction)
        Spacer(modifier = Modifier.height(16.dp))
        BottomSection(events = uiState.events, onAction = onAction)
    }
}

@Composable
fun TopSection(onAction: (SpecialDayContract.UiAction) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Özel Günler",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFD32F2F)
        )
        Text(
            text = "Lütfen bir özel gün seçiniz",
            fontSize = 16.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            SpecialDayItem(
                iconRes = R.drawable.special_day_1,
                text = "Evlilik Yıldönümü"
            ) {
                onAction(SpecialDayContract.UiAction.OnSpecialDayClick(SpecialDayContract.SpecialDayType.ANNIVERSARY))
            }
            SpecialDayItem(
                iconRes = R.drawable.special_day_2,
                text = "Tanışma Yıldönümü"
            ) {
                onAction(SpecialDayContract.UiAction.OnSpecialDayClick(SpecialDayContract.SpecialDayType.MEETING_ANNIVERSARY))
            }
            SpecialDayItem(
                iconRes = R.drawable.special_day_3,
                text = "Doğum Günü"
            ) {
                onAction(SpecialDayContract.UiAction.OnSpecialDayClick(SpecialDayContract.SpecialDayType.BIRTHDAY))
            }
            SpecialDayItem(
                iconRes = R.drawable.special_day_4,
                text = "Diğer"
            ) {
                onAction(SpecialDayContract.UiAction.OnSpecialDayClick(SpecialDayContract.SpecialDayType.OTHER))
            }
        }
    }
}

@Composable
fun SpecialDayItem(iconRes: Int, text: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = text,
            modifier = Modifier.size(36.dp)
        )
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = text,
            fontSize = 10.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun EventListItem(
    event: Event,
    onAction: (SpecialDayContract.UiAction) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp, horizontal = 12.dp)
            .clickable { onAction(SpecialDayContract.UiAction.OnEventClick(event)) },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0x4BFFFFFF))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = event.imageRes),
                contentDescription = event.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            Spacer(modifier = Modifier.size(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = event.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color.White
                )
                HorizontalDivider(
                    color = Color.White,
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 6.dp)
                )
                Row {
                    Column {
                        Text(text = "Tarih", fontSize = 10.sp, color = Color.White)
                        Text(
                            text = event.date,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.size(8.dp))
                    Column {
                        Text(text = "Saat", fontSize = 10.sp, color = Color.White)
                        Text(
                            text = event.time,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.size(8.dp))
            Image(
                painter = painterResource(id = event.iconRes),
                contentDescription = null,
                modifier = Modifier.size(36.dp)
            )
        }
    }
}


@Composable
fun BottomSection(
    events: List<Event>,
    onAction: (SpecialDayContract.UiAction) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .background(Color(0xFFE57373)),
            contentAlignment = Alignment.TopCenter
        ) {
            if (events.isEmpty()) {
                LCIcon(
                    vector = LCTheme.icons.cat,
                    contentDescription = "Kedi",
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                )
            } else {
                LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
                    items(events) { event ->
                        EventListItem(event = event, onAction = onAction)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Empty State")
@Composable
fun ProfileScreenPreview() {
    SpecialDaysScreen(
        uiState = SpecialDayContract.UiState(),
        onAction = {}
    )
}

@Preview(showBackground = true, name = "With Data")
@Composable
fun ProfileScreenWithDataPreview() {
    val mockEvents = listOf(
        Event(
            1,
            R.drawable.special_day_1,
            "Evlilik Yıl Dönümü",
            "20.05.1993",
            "13:00",
            R.drawable.special_day_1
        ),
        Event(
            2,
            R.drawable.special_day_2,
            "Evlilik Yıl Dönümü",
            "20.05.1993",
            "13:00",
            R.drawable.special_day_2
        ),
        Event(
            3,
            R.drawable.special_day_3,
            "Evlilik Yıl Dönümü",
            "20.05.1993",
            "13:00",
            R.drawable.special_day_3
        ),
        Event(
            4,
            R.drawable.special_day_4,
            "Evlilik Yıl Dönümü",
            "20.05.1993",
            "13:00",
            R.drawable.special_day_4
        )
    )
    SpecialDaysScreen(
        uiState = SpecialDayContract.UiState(events = mockEvents),
        onAction = {}
    )
}
