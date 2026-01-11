package com.example.lovecounter.presentation.datingstories

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import com.example.lovecounter.data.model.DatingStoryEntity
import com.example.lovecounter.presentation.theme.LCTheme

@Composable
fun DatingStoriesScreen(
    uiState: DatingStoriesContract.UiState,
    onAction: (DatingStoriesContract.UiAction) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        DatingStoriesBackground()

        Column(modifier = Modifier.fillMaxSize()) {
            DatingStoriesHeader()

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
                    contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(uiState.stories) { story ->
                        DatingStoryItem(
                            story = story,
                            onClick = {
                                onAction(DatingStoriesContract.UiAction.OnStoryClick(story))
                            }
                        )
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = { onAction(DatingStoriesContract.UiAction.OnAddStoryClick) },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 80.dp),
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Hikaye Ekle"
            )
        }
    }
}

@Composable
private fun DatingStoriesBackground() {
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
private fun DatingStoriesHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp, start = 24.dp, end = 24.dp, bottom = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Tanışma\nHikayeleri",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            lineHeight = 32.sp
        )

        // Phone/message icon (using a placeholder drawable)
        Icon(
            painter = painterResource(id = R.drawable.fakephoto),
            contentDescription = "Hikayeler",
            modifier = Modifier.size(80.dp),
            tint = Color.Unspecified
        )
    }
}

@Composable
private fun DatingStoryItem(
    story: DatingStoryEntity,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Circular Photo
        Image(
            painter = painterResource(id = story.photoResId),
            contentDescription = story.title,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        // Content Column
        Column(
            modifier = Modifier.weight(1f)
        ) {
            // Title
            Text(
                text = story.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            // Description
            Text(
                text = story.description,
                fontSize = 13.sp,
                color = Color.White.copy(alpha = 0.9f),
                lineHeight = 18.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DatingStoriesScreenPreview() {
    LCTheme {
        DatingStoriesScreen(
            uiState = DatingStoriesContract.UiState(
                stories = listOf(
                    DatingStoryEntity(
                        id = 1,
                        title = "Buraya Başlık Gelecek",
                        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                        photoResId = R.drawable.fakephoto
                    ),
                    DatingStoryEntity(
                        id = 2,
                        title = "Buraya Başlık Gelecek",
                        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                        photoResId = R.drawable.fakephoto
                    ),
                    DatingStoryEntity(
                        id = 3,
                        title = "Buraya Başlık Gelecek",
                        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                        photoResId = R.drawable.fakephoto
                    )
                ),
                isLoading = false
            ),
            onAction = {}
        )
    }
}
