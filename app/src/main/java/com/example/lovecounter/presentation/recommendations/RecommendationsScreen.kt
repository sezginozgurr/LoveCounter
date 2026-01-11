package com.example.lovecounter.presentation.recommendations

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lovecounter.R
import com.example.lovecounter.data.model.RecommendationEntity
import com.example.lovecounter.presentation.theme.LCTheme

@Composable
fun RecommendationsScreen(
    uiState: RecommendationsContract.UiState,
    onAction: (RecommendationsContract.UiAction) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        RecommendationsBackground()

        Column(modifier = Modifier.fillMaxSize()) {
            RecommendationsHeader()

            if (uiState.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color.White)
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    items(uiState.recommendationEntities) { recommendation ->
                        RecommendationCard(
                            recommendationEntity = recommendation,
                            onLikeClick = {
                                onAction(RecommendationsContract.UiAction.OnLikeClick(recommendation))
                            },
                            onShareClick = {
                                onAction(RecommendationsContract.UiAction.OnShareClick(recommendation))
                            },
                            onClick = {
                                onAction(RecommendationsContract.UiAction.OnRecommendationClick(recommendation))
                            }
                        )
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = { onAction(RecommendationsContract.UiAction.OnAddRecommendationClick) },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 80.dp),
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Tavsiye Ekle"
            )
        }
    }
}

@Composable
private fun RecommendationsBackground() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFF9488),
                        Color(0xFFF97068)
                    )
                )
            )
    ) {
        Image(
            imageVector = LCTheme.icons.whiteBg,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter),
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
private fun RecommendationsHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp, start = 24.dp, end = 24.dp, bottom = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Size Özel\nTavsiyeler",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            lineHeight = 32.sp
        )

        // Envelope icon (using a placeholder drawable)
        Icon(
            painter = painterResource(id = R.drawable.fakephoto),
            contentDescription = "Tavsiyeler",
            modifier = Modifier.size(80.dp),
            tint = Color.Unspecified
        )
    }
}

@Composable
private fun RecommendationCard(
    recommendationEntity: RecommendationEntity,
    onLikeClick: () -> Unit,
    onShareClick: () -> Unit,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Couple Photo
            Image(
                painter = painterResource(id = recommendationEntity.photoResId),
                contentDescription = recommendationEntity.coupleName,
                modifier = Modifier
                    .width(100.dp)
                    .height(150.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            // Content Column
            Column(
                modifier = Modifier
                    .weight(1f)
                    .height(150.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    // Couple Name
                    Text(
                        text = recommendationEntity.coupleName,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    // Duration
                    Text(
                        text = recommendationEntity.duration,
                        fontSize = 12.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 4.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Description
                    Text(
                        text = recommendationEntity.description,
                        fontSize = 12.sp,
                        color = Color.DarkGray,
                        lineHeight = 16.sp,
                        maxLines = 4
                    )
                }

                // Action Buttons Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Like Button
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable(onClick = onLikeClick)
                    ) {
                        Icon(
                            imageVector = if (recommendationEntity.isLiked) {
                                Icons.Default.Favorite
                            } else {
                                Icons.Default.FavoriteBorder
                            },
                            contentDescription = "Beğen",
                            tint = if (recommendationEntity.isLiked) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                Color.Gray
                            },
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = "Beğen",
                            fontSize = 11.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    // Share Button
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable(onClick = onShareClick)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Paylaş",
                            tint = Color.Gray,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = "Paylaş",
                            fontSize = 11.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RecommendationsScreenPreview() {
    LCTheme {
        RecommendationsScreen(
            uiState = RecommendationsContract.UiState(
                recommendationEntities = listOf(
                    RecommendationEntity(
                        id = 1,
                        coupleName = "Merve & Özgür",
                        duration = "10 Yıl 5 Ay 20 Gün",
                        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s",
                        photoResId = R.drawable.fakephoto
                    ),
                    RecommendationEntity(
                        id = 2,
                        coupleName = "Merve & Özgür",
                        duration = "10 Yıl 5 Ay 20 Gün",
                        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s",
                        photoResId = R.drawable.fakephoto
                    )
                ),
                isLoading = false
            ),
            onAction = {}
        )
    }
}
