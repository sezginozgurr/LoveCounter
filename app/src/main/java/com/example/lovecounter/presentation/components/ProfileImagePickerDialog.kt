package com.example.lovecounter.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lovecounter.R
import com.example.lovecounter.presentation.theme.LCTheme

enum class Gender {
    MALE, FEMALE
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileImagePickerDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onImageSelected: (Int) -> Unit,
    gender: Gender,
) {
    val imageList = remember(gender) {
        when (gender) {
            Gender.MALE -> listOf(
                R.drawable.ic_profile_black,
                R.drawable.ic_profile_black,
                R.drawable.ic_profile_black,
            )

            Gender.FEMALE -> listOf(
                R.drawable.ic_profile_red,
                R.drawable.ic_profile_yellow,
                R.drawable.ic_profile_orange,
            )
        }
    }

    var selectedImageIndex by remember { mutableStateOf(0) }

    if (showDialog) {
        ModalBottomSheet(
            onDismissRequest = onDismiss,
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
            dragHandle = null,
            containerColor = Color.Transparent,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.weight(1f))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(
                            color = Color.White,
                            shape = object : Shape {
                                override fun createOutline(
                                    size: Size,
                                    layoutDirection: LayoutDirection,
                                    density: Density,
                                ): Outline {
                                    val path = Path().apply {
                                        moveTo(0f, size.height * 0.25f)
                                        quadraticTo(
                                            size.width / 2f, 0f,
                                            size.width, size.height * 0.25f
                                        )
                                        lineTo(size.width, size.height)
                                        lineTo(0f, size.height)
                                        close()
                                    }
                                    return Outline.Generic(path)
                                }
                            }
                        )
                        .padding(bottom = 48.dp, start = 16.dp, end = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .offset(y = (-60).dp)
                            .size(160.dp)
                            .dropShadow(CircleShape) {
                                radius = 20f
                                alpha = 0.5f
                            }
                            .background(Color.White, CircleShape)
                            .padding(12.dp),
                        contentAlignment = Alignment.BottomCenter,
                    ) {
                        Image(
                            painter = painterResource(id = imageList[selectedImageIndex]),
                            contentDescription = "Selected avatar",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.FillHeight,
                        )
                    }

                    Text(
                        text = "Size En Uygun Karakteri Seçiniz",
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        imageList.forEachIndexed { index, imageRes ->
                            Box(
                                modifier = Modifier
                                    .width(80.dp)
                                    .aspectRatio(9f / 11f)
                                    .clickable {
                                        selectedImageIndex = index
                                        onImageSelected(imageRes)
                                        onDismiss()
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = imageRes),
                                    contentDescription = "Avatar option $index",
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .width(80.dp),
                                    contentScale = ContentScale.FillWidth,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileImagePickerDialogPreview() {
    LCTheme {
        ProfileImagePickerDialog(
            showDialog = true,
            onDismiss = {},
            onImageSelected = {},
            gender = Gender.FEMALE,
        )
    }
}