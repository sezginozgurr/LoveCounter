package com.example.lovecounter.presentation.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lovecounter.presentation.theme.LCTheme

@Composable
fun SplashScreen(
    uiState: SplashContract.UiState,
    onAction: (SplashContract.UiAction) -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (uiState.isLoading) {
            CircularProgressIndicator()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SplashScreenPreview() {
    LCTheme {
        SplashScreen(
            uiState = SplashContract.UiState(isLoading = true),
            onAction = {}
        )
    }
}