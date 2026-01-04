package com.example.lovecounter.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollScope
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.lovecounter.R
import com.example.lovecounter.presentation.theme.AppColor
import kotlinx.coroutines.launch
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos

@Composable
fun CurvedScrollView(
    itemCount: Int = 5,
    scrollState: ScrollState,
    item: @Composable (Int, Boolean) -> Unit,
) {
    var size = remember { mutableStateOf(IntSize.Zero) }
    val scope = rememberCoroutineScope()
    val indices = remember { IntArray(itemCount) { 0 } }

    val scrollPercent by remember(scrollState.value) {
        derivedStateOf { scrollState.value.toFloat() / scrollState.maxValue }
    }

    val flingBehaviour = remember {
        object : FlingBehavior {
            override suspend fun ScrollScope.performFling(initialVelocity: Float): Float {
                val value = scrollState.value
                indices.minByOrNull { abs(it - value) }?.let {
                    scope.launch {
                        scrollState.animateScrollTo(it)
                    }
                }
                return initialVelocity
            }
        }
    }

    Box(
        modifier = Modifier.onSizeChanged { size.value = it }
    ) {
        Layout(
            content = {
                for (index in 0 until itemCount) {
                    key(index) {
                        val elementRatio = index.toFloat() / (itemCount - 1)
                        val isSelected = abs(scrollPercent - elementRatio) < 0.15
                        item(index, isSelected)
                    }
                }
            },
            modifier = Modifier.horizontalScroll(scrollState, flingBehavior = flingBehaviour)
        ) { measurables, constraints ->
            val itemSpacing = 0.dp.roundToPx()
            var contentWidth = (itemCount - 1) * itemSpacing

            val placeables = measurables.mapIndexed { index, measurable ->
                val placeable = measurable.measure(constraints = constraints)
                contentWidth += if (index == 0 || index == measurables.lastIndex) {
                    placeable.width / 2
                } else {
                    placeable.width
                }
                placeable
            }

            layout(size.value.width + contentWidth, constraints.maxHeight) {
                val startOffset = size.value.width / 2 - placeables[0].width / 2
                var xPosition = startOffset

                placeables.forEachIndexed { index, placeable ->
                    val elementRatio = index.toFloat() / placeables.lastIndex
                    val interpolatedValue = cos((scrollPercent - elementRatio) * PI)
                    val indent = interpolatedValue * size.value.height / 6

                    placeable.placeRelativeWithLayer(
                        x = xPosition,
                        y = (size.value.height - indent.toInt()) - placeable.height
                    ) {
                        alpha = 1f
                    }
                    indices[index] = xPosition - startOffset
                    xPosition += placeable.width + itemSpacing
                }
            }
        }
    }
}

// Cinsiyet için enum class ekleyelim
enum class Gender {
    MALE, FEMALE
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileImagePickerDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onImageSelected: (Int) -> Unit,
    gender: Gender, // Yeni parametre ekledik
) {
    val imageList = remember(gender) {
        when (gender) {
            Gender.MALE -> listOf(
                R.drawable.ic_profile_black,
                R.drawable.ic_profile_black,
                R.drawable.ic_profile_black,
                R.drawable.ic_profile_black,
                R.drawable.ic_profile_black,
            )

            Gender.FEMALE -> listOf(
                R.drawable.ic_profile_repred,
                R.drawable.ic_profile_orange,
                R.drawable.ic_profile_black,
                R.drawable.ic_profile_yellow,
            )
        }
    }

    if (showDialog) {
        ModalBottomSheet(
            modifier = Modifier.wrapContentHeight(),
            onDismissRequest = onDismiss,
            sheetState = rememberModalBottomSheetState(
                skipPartiallyExpanded = true
            ),
            dragHandle = null,
            containerColor = Color.White,
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = if (gender == Gender.MALE) "Erkek Profil Fotoğrafı Seç"
                    else "Kadın Profil Fotoğrafı Seç",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(32.dp))

                Box(
                    modifier = Modifier
                        .height(120.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    val scrollState = rememberScrollState()

                    val imageContent = @Composable { index: Int, isSelected: Boolean ->
                        Box(
                            modifier = Modifier
                                .wrapContentSize()
                                .padding(horizontal = 56.dp)
                                .then(
                                    if (isSelected) {
                                        Modifier.background(
                                            color = Color(0xFF00EE0A),
                                            shape = CircleShape
                                        )
                                    } else Modifier
                                )
                        ) {
                            Image(
                                painter = painterResource(id = imageList[index]),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .then(
                                        if (isSelected) {
                                            Modifier.clickable {
                                                onImageSelected(imageList[index])
                                                onDismiss()
                                            }
                                        } else Modifier
                                    ),
                                contentScale = ContentScale.Fit
                            )
                        }
                    }

                    CurvedScrollView(
                        itemCount = imageList.size,
                        scrollState = scrollState,
                        item = imageContent
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = onDismiss,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AppColor
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Seç",
                        color = Color.White,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}