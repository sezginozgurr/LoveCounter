package com.example.lovecounter.presentation.memories

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lovecounter.data.model.MemoryEntity
import com.example.lovecounter.presentation.components.LCIcon
import com.example.lovecounter.presentation.theme.LCTheme

@Composable
fun MemoriesScreen(
    uiState: MemoriesContract.UiState,
    onAction: (MemoriesContract.UiAction) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        MemoriesBackground()

        if (uiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(uiState.memories) { memory ->
                    MemoryItem(
                        memoryEntity = memory,
                        onClick = { onAction(MemoriesContract.UiAction.OnMemoryClick(memory)) }
                    )
                }
            }
        }

        FloatingActionButton(
            onClick = { onAction(MemoriesContract.UiAction.OnAddMemoryClick) },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            LCIcon(
                vector = Icons.Default.Add,
            )
        }
    }
}

@Composable
private fun MemoriesBackground() {
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
private fun MemoryItem(
    memoryEntity: MemoryEntity,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = memoryEntity.title,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
            if (memoryEntity.subtitle.isNotEmpty()) {
                Text(
                    text = memoryEntity.subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MemoriesScreenPreview() {
    LCTheme {
        MemoriesScreen(
            uiState = MemoriesContract.UiState(
                memories = listOf(
                    MemoryEntity(id = 1, title = "A Special Day", subtitle = "Our first date"),
                    MemoryEntity(id = 2, title = "Vacation", subtitle = "Trip to the beach"),
                    MemoryEntity(id = 3, title = "Anniversary", subtitle = "Celebrating together")
                ),
                isLoading = false
            ),
            onAction = {}
        )
    }
}