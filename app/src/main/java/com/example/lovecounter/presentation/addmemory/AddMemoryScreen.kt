package com.example.lovecounter.presentation.addmemory

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.lovecounter.presentation.theme.LCTheme

@Composable
fun AddMemoryScreen(
    uiState: AddMemoryContract.UiState,
    onAction: (AddMemoryContract.UiAction) -> Unit,
) {
    val multiplePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(maxItems = 5),
        onResult = { uris -> onAction(AddMemoryContract.UiAction.OnImagesSelected(uris)) }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedTextField(
            value = uiState.title,
            onValueChange = { onAction(AddMemoryContract.UiAction.OnTitleChange(it)) },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth(),
            enabled = !uiState.isSaving
        )
        OutlinedTextField(
            value = uiState.subtitle,
            onValueChange = { onAction(AddMemoryContract.UiAction.OnSubtitleChange(it)) },
            label = { Text("Subtitle") },
            modifier = Modifier.fillMaxWidth(),
            enabled = !uiState.isSaving
        )
        Button(
            onClick = {
                multiplePhotoPickerLauncher.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo)
                )
            },
            enabled = !uiState.isSaving
        ) {
            Text(text = "Pick photos")
        }

        LazyRow(
            modifier = Modifier.padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(uiState.selectedImageUris) { uri ->
                AsyncImage(
                    model = uri,
                    contentDescription = null,
                    modifier = Modifier.size(100.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }

        Button(
            onClick = { onAction(AddMemoryContract.UiAction.OnSaveClick) },
            modifier = Modifier.padding(top = 16.dp),
            enabled = !uiState.isSaving && uiState.title.isNotBlank()
        ) {
            if (uiState.isSaving) {
                CircularProgressIndicator()
            } else {
                Text(text = "Save Memory")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AddMemoryScreenPreview() {
    LCTheme {
        AddMemoryScreen(
            uiState = AddMemoryContract.UiState(
                title = "A Special Day",
                subtitle = "Our first date",
                selectedImageUris = listOf(),
                isSaving = false
            ),
            onAction = {}
        )
    }
}