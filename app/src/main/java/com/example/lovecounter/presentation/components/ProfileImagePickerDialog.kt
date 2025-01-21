package com.example.lovecounter.presentation.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollScope
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.foundation.layout.WindowInsets
import com.example.lovecounter.R
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.PI

@Composable
fun CurvedScrollView(
    itemCount: Int,
    scrollState: ScrollState,
    item: @Composable (Int) -> Unit,
) {
    val density = LocalDensity.current
    var size = remember { mutableStateOf(IntSize.Zero) }
    val scope = rememberCoroutineScope()
    val indices = remember { IntArray(itemCount) { 0 } }

    val flingBehaviour = object : FlingBehavior {
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

    Box(
        modifier = Modifier
            .height(180.dp)
            .onSizeChanged {
                size.value = it
            }
    ) {
        Layout(
            content = {
                repeat(itemCount) {
                    item(it)
                }
            },
            modifier = Modifier.horizontalScroll(
                scrollState, flingBehavior = flingBehaviour
            )
        ) { measurables, constraints ->
            val itemSpacing = with(density) { 16.dp.toPx() }.toInt()
            var contentWidth = (itemCount - 1) * itemSpacing
            
            val placeables = measurables.mapIndexed { index, measurable ->
                val placeable = measurable.measure(constraints = constraints)
                contentWidth += placeable.width
                placeable
            }

            layout(size.value.width + contentWidth, constraints.maxHeight) {
                val startOffset = size.value.width / 2 - placeables[0].width / 2
                var xPosition = startOffset
                val scrollPercent = if (scrollState.maxValue != 0) {
                    scrollState.value.toFloat() / scrollState.maxValue
                } else {
                    0f
                }

                placeables.forEachIndexed { index, placeable ->
                    val elementRatio = index.toFloat() / (placeables.lastIndex.coerceAtLeast(1))
                    val interpolatedValue = cos((scrollPercent - elementRatio) * PI)
                    val indent = interpolatedValue * size.value.height / 3.8
                    
                    val distanceFromCenter = abs(scrollPercent - elementRatio)
                    val scale = if (distanceFromCenter < 0.5f) 1.1f else 0.8f
                    val yOffset = if (distanceFromCenter < 0.5f) 0 else 20

                    placeable.placeRelativeWithLayer(
                        x = xPosition,
                        y = (size.value.height - indent - placeable.height).toInt() + yOffset,
                        layerBlock = {
                            alpha = if (distanceFromCenter < 0.5f) 1f else 0.7f
                            scaleX = scale
                            scaleY = scale
                        }
                    )
                    indices[index] = xPosition - startOffset
                    xPosition += placeable.width + itemSpacing
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileImagePickerDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onImageSelected: (Int) -> Unit
) {
    val imageList = listOf(
        R.drawable.home_default_profile_male,
        R.drawable.home_default_profile_male,
        R.drawable.home_default_profile_male,
        R.drawable.home_default_profile_male,
        R.drawable.home_default_profile_male,
        R.drawable.home_default_profile_male
    )

    if (showDialog) {
        ModalBottomSheet(
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
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Profil Fotoğrafı Seç",
                    style = MaterialTheme.typography.titleMedium
                )
                
                Spacer(modifier = Modifier.height(32.dp))
                
                val scrollState = rememberScrollState()
                CurvedScrollView(
                    itemCount = imageList.size,
                    scrollState = scrollState
                ) { index ->
                    Image(
                        painter = painterResource(id = imageList[index]),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .size(120.dp)
                            .clickable {
                                onImageSelected(imageList[index])
                                onDismiss()
                            },
                        contentScale = ContentScale.Crop
                    )
                }
                
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
} 