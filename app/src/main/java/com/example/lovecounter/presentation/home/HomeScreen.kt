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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lovecounter.R
import com.example.lovecounter.presentation.components.CustomDatePicker
import com.example.lovecounter.presentation.components.Gender
import com.example.lovecounter.presentation.components.ProfileImagePickerDialog
import com.example.lovecounter.presentation.theme.AppColor
import com.example.lovecounter.presentation.theme.White

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(AppColor)
            .verticalScroll(rememberScrollState())
    ) {
        Card(
            Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.fakephoto),
                contentDescription = "banner",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Text(
            text = "İlişkiniz 5. yıla ulaşmıştır. Tokiden ev alabilirsiniz",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Color.White
        )

        ProfilePictures(onClickMale = {}, onClickFemale = {}) //click profile

        DaySpend(viewModel)

        Text(
            text = "Birlikte Geçirilen zaman 5 yıl 15 ay 15 gün",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            textAlign = TextAlign.Center,
            color = Color.White
        )

        Recommendations()

        DatingStory()
    }
}

@Composable
private fun Recommendations() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
            .background(White)
    ) {
        val list = listOf("1", "2", "3", "4", "5")
        Row(
            Modifier.padding(top = 12.dp, start = 12.dp, end = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Tavsiyeler",
                fontWeight = FontWeight.Bold,
                color = AppColor,
                fontSize = 10.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier
                    .background(AppColor, CircleShape)
                    .padding(horizontal = 12.dp, vertical = 4.dp),
                text = "Daha Fazla",
                fontWeight = FontWeight.Bold,
                color = White,
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
            .background(White)
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
                color = AppColor,
                fontSize = 10.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Daha Fazla",
                fontWeight = FontWeight.Bold,
                color = White,
                modifier = Modifier
                    .background(AppColor, CircleShape)
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
private fun DaySpend(viewModel: HomeViewModel) {
    var showDatePicker by remember { mutableStateOf(false) }
    val duration by viewModel.relationshipDuration.collectAsState()
    val isDateSelected by viewModel.isDateSelected.collectAsState()

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(
            36.dp,
            alignment = Alignment.CenterHorizontally
        ),
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
            modifier = Modifier.clickable { showDatePicker = true }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.year_bg_firstly),
                contentDescription = "a",
                tint = Color.Unspecified
            )
            Icon(
                painter = painterResource(id = R.drawable.year_bg_secondly),
                contentDescription = "b",
                tint = Color.Unspecified
            )

            // Tarih seçilmemişse calendar ikonunu göster
            if (!isDateSelected) {
                Icon(
                    modifier = Modifier.size(52.dp),
                    painter = painterResource(id = R.drawable.ic_choose_date),
                    contentDescription = "calendar",
                    tint = Color.Unspecified
                )
            }

            // Tarih seçilmişse yıl bilgisini göster
            if (isDateSelected) {
                Text(
                    text = "${duration.years}\nyıl",
                    color = AppColor,
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

    CustomDatePicker(
        showDialog = showDatePicker,
        onDismiss = { showDatePicker = false },
        onDateSelected = { selectedDate ->
            viewModel.updateStartDate(selectedDate)
        }
    )
}

@Composable
private fun ProfilePictures(onClickMale: () -> Unit, onClickFemale: () -> Unit) {
    var showMaleImagePicker by remember { mutableStateOf(false) }
    var showFemaleImagePicker by remember { mutableStateOf(false) }

    var maleProfileImage by remember { mutableIntStateOf(R.drawable.home_default_profile_male) }
    var femaleProfileImage by remember { mutableIntStateOf(R.drawable.home_default_profile_female) }

    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Image(
                painter = painterResource(id = maleProfileImage),
                contentDescription = "Erkek profil",
                modifier = Modifier
                    .width(72.dp)
                    .height(72.dp)
                    .clickable { showMaleImagePicker = true },
                contentScale = ContentScale.Crop
            )
            Text(
                text = "Erkek",
                modifier = Modifier.padding(start = 16.dp, top = 8.dp),
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }

        Column {
            Image(
                painter = painterResource(id = femaleProfileImage),
                contentDescription = "Kadın profil",
                modifier = Modifier
                    .width(72.dp)
                    .height(72.dp)
                    .clickable { showFemaleImagePicker = true },
                contentScale = ContentScale.Crop
            )
            Text(
                text = "Kadın",
                modifier = Modifier.padding(start = 16.dp, top = 8.dp),
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }
    }

    // Erkek profil fotoğrafı seçici
    ProfileImagePickerDialog(
        showDialog = showMaleImagePicker,
        onDismiss = { showMaleImagePicker = false },
        onImageSelected = { selectedImage ->
            maleProfileImage = selectedImage
        },
        gender = Gender.MALE
    )

    // Kadın profil fotoğrafı seçici
    ProfileImagePickerDialog(
        showDialog = showFemaleImagePicker,
        onDismiss = { showFemaleImagePicker = false },
        onImageSelected = { selectedImage ->
            femaleProfileImage = selectedImage
        },
        gender = Gender.FEMALE
    )
}

@Preview
@Composable
fun Preview() {
    HomeScreen()
}
