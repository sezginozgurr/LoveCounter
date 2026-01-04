package com.example.lovecounter.presentation.settings

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.lovecounter.R
import com.example.lovecounter.presentation.theme.MyappTheme

@Composable
fun SettingsScreen(
    onAddMemory: () -> Unit = {},
    onItemClick: (Int) -> Unit = {},
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DailyMemoriesPanel(onAddMemory = onAddMemory, onItemClick = onItemClick)
        }
    }
}

@Composable
private fun DailyMemoriesPanel(
    onAddMemory: () -> Unit,
    onItemClick: (Int) -> Unit,
) {
    Box {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Column {
                Header()
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    MemoryItem(
                        title = stringResource(id = R.string.title),
                        subtitle = stringResource(id = R.string.subtitle),
                        onClick = { onItemClick(0) }
                    )
                    (1..3).forEach {
                        MemoryItem(
                            title = stringResource(id = R.string.title),
                            subtitle = stringResource(id = R.string.subtitle),
                            onClick = { onItemClick(it) }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(28.dp))
            }
        }
        FloatingActionButton(
            onClick = onAddMemory,
            modifier = Modifier.align(Alignment.BottomCenter),
            containerColor = MaterialTheme.colorScheme.primary
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(id = R.string.add)
            )
        }
    }
}

@Composable
private fun Header() {
    val headerHeight = 150.dp
    val density = LocalDensity.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(headerHeight)
            .clip(CutHeaderShape(density))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = stringResource(id = R.string.daily_memories),
                    style = MaterialTheme.typography.headlineMedium.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.semantics { heading() }
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_heart),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_top_memories),
                contentDescription = null,
                tint = Color.Unspecified,
            )
        }
    }
}


@Composable
private fun MemoryItem(
    title: String,
    subtitle: String,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

private class CutHeaderShape(private val density: Density) : Shape {
    override fun createOutline(
        size: androidx.compose.ui.geometry.Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): androidx.compose.ui.graphics.Outline {
        val path = Path().apply {
            lineTo(0f, size.height * 0.7f)
            quadraticTo(
                size.width / 2f,
                size.height,
                size.width,
                size.height * 0.7f
            )
            lineTo(size.width, 0f)
            close()
        }
        return androidx.compose.ui.graphics.Outline.Generic(path)
    }
}


@Preview(showBackground = true, name = "Light Mode")
@Composable
fun SettingsScreenPreview() {
    MyappTheme {
        SettingsScreen()
    }
}

@Preview(showBackground = true, name = "Dark Mode")
@Composable
fun SettingsScreenDarkPreview() {
    MyappTheme(darkTheme = true) {
        SettingsScreen()
    }
}