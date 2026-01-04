package com.example.lovecounter.presentation.home

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import com.example.lovecounter.presentation.components.Gender
import com.example.lovecounter.presentation.components.ProfileImagePickerDialog
import com.example.lovecounter.presentation.theme.LCTheme

@Composable
fun HomeScreen(
    uiState: HomeContract.UiState,
    onAction: (HomeContract.UiAction) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .verticalScroll(rememberScrollState()),
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp)
                .clip(RoundedCornerShape(8.dp)),
            painter = painterResource(id = R.drawable.fakephoto),
            contentDescription = "banner",
            contentScale = ContentScale.Crop,
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "İlişkiniz 5. yıla ulaşmıştır. Tokiden ev alabilirsiniz",
            textAlign = TextAlign.Center,
            color = Color.White,
        )

        ProfilePictures(
            maleImage = uiState.maleImage,
            femaleImage = uiState.femaleImage,
            onAction = onAction,
        )

        DaySpend(
            duration = uiState.relationshipDuration,
            isDateSelected = uiState.isDateSelected,
            onDateClick = { onAction(HomeContract.UiAction.OnSelectDateClick) }
        )

        Recommendations()

        DatingStory()
    }

    ProfileImagePickerDialog(
        showDialog = uiState.showMaleImagePicker,
        onDismiss = { onAction(HomeContract.UiAction.OnDismissMaleImagePicker) },
        onImageSelected = { onAction(HomeContract.UiAction.OnMaleImageSelected(it)) },
        gender = Gender.MALE
    )

    ProfileImagePickerDialog(
        showDialog = uiState.showFemaleImagePicker,
        onDismiss = { onAction(HomeContract.UiAction.OnDismissFemaleImagePicker) },
        onImageSelected = { onAction(HomeContract.UiAction.OnFemaleImageSelected(it)) },
        gender = Gender.FEMALE
    )
}

@Composable
private fun Recommendations() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        val list = listOf("1", "2", "3", "4", "5")
        Row(
            Modifier.padding(top = 12.dp, start = 12.dp, end = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Tavsiyeler",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 10.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary, CircleShape)
                    .padding(horizontal = 12.dp, vertical = 4.dp),
                text = "Daha Fazla",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.background,
                fontSize = 10.sp
            )
        }
        LazyRow {
            items(list) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Card(
                        modifier = Modifier
                            .width(140.dp)
                            .height(200.dp)
                            .padding(8.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.fakephoto),
                            contentDescription = "c",
                            contentScale = ContentScale.Crop
                        )
                    }
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally),
                        text = "Öneri Başlık",
                        textAlign = TextAlign.Center,
                        fontSize = 10.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun DatingStory() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(bottom = 16.dp)
    ) {
        val list = listOf("1", "2", "3", "4")
        Row(
            Modifier.padding(top = 12.dp, start = 12.dp, end = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Tanışma Hikayeleri",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 10.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Daha Fazla",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary, CircleShape)
                    .padding(horizontal = 12.dp, vertical = 4.dp),
                fontSize = 10.sp
            )
        }
        LazyRow {
            items(list) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Card(
                        modifier = Modifier
                            .width(140.dp)
                            .height(200.dp)
                            .padding(8.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.fakephoto),
                            contentDescription = "c",
                            contentScale = ContentScale.Crop
                        )
                    }
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally),
                        text = "Tanışma Hikayesi Başlık",
                        textAlign = TextAlign.Center,
                        fontSize = 10.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun DaySpend(
    duration: RelationshipDuration,
    isDateSelected: Boolean,
    onDateClick: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(36.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${duration.days}\ngün",
            color = Color.White,
            fontSize = 40.sp,
            lineHeight = 36.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.clickable { onDateClick() }
        ) {
            Icon(
                imageVector = LCTheme.icons.yearBgFirstly,
                contentDescription = "a",
                tint = Color.Unspecified
            )
            Icon(
                imageVector = LCTheme.icons.yearBgSecondly,
                contentDescription = "b",
                tint = Color.Unspecified
            )

            if (!isDateSelected) {
                Icon(
                    modifier = Modifier.size(52.dp),
                    imageVector = LCTheme.icons.chooseDate,
                    contentDescription = "calendar",
                    tint = Color.Unspecified
                )
            }

            if (isDateSelected) {
                Text(
                    text = "${duration.years}\nyıl",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 40.sp,
                    lineHeight = 36.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }

        Text(
            text = "${duration.months}\nay",
            color = Color.White,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 36.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun ProfilePictures(
    maleImage: Int?,
    femaleImage: Int?,
    onAction: (HomeContract.UiAction) -> Unit,
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column {
            Image(
                modifier = Modifier
                    .width(72.dp)
                    .height(72.dp)
                    .clickable { onAction(HomeContract.UiAction.OnMaleImageClick) },
                painter = painterResource(id = maleImage ?: R.drawable.home_default_profile_male),
                contentDescription = "Erkek profil",
                contentScale = ContentScale.Crop,
            )
            Text(
                modifier = Modifier.padding(start = 16.dp, top = 8.dp),
                text = "Erkek",
                textAlign = TextAlign.Center,
                color = Color.White,
            )
        }

        Column {
            Image(
                modifier = Modifier
                    .width(72.dp)
                    .height(72.dp)
                    .clickable { onAction(HomeContract.UiAction.OnFemaleImageClick) },
                painter = painterResource(id = femaleImage ?: R.drawable.home_default_profile_female),
                contentDescription = "Kadın profil",
                contentScale = ContentScale.Crop,
            )
            Text(
                modifier = Modifier.padding(start = 16.dp, top = 8.dp),
                text = "Kadın",
                textAlign = TextAlign.Center,
                color = Color.White,
            )
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        uiState = HomeContract.UiState(
            relationshipDuration = RelationshipDuration(2, 3, 10),
            isDateSelected = true,
        ),
        onAction = {},
    )
}
