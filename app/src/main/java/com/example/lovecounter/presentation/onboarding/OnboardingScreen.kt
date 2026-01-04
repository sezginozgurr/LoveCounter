package com.example.lovecounter.presentation.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lovecounter.R
import com.example.lovecounter.presentation.onboarding.OnboardingContract.UiState
import com.example.lovecounter.presentation.theme.SoulMatesOrangeEndColor
import com.example.lovecounter.presentation.theme.SoulMatesOrangeStartColor
import com.example.lovecounter.presentation.theme.White

@Composable
fun OnboardingScreen(
    onFinishClicked: () -> Unit,
) {
    val onboardingPages = getOnboardingData()
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) { onboardingPages.size }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(SoulMatesOrangeStartColor, SoulMatesOrangeEndColor)
                )
            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalPager(
                state = pagerState,
                pageSize = PageSize.Fill,
                modifier = Modifier.weight(1f)
            ) { page ->
                PageContent(
                    onboardingData = onboardingPages[page]
                )
            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 100.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(onboardingPages.size) { iteration ->
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(
                            if (pagerState.currentPage == iteration) White
                            else White.copy(alpha = 0.5f)
                        )
                )
            }
        }

        if (pagerState.currentPage == onboardingPages.size - 1) {
            Button(
                onClick = onFinishClicked,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = White),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Get Started",
                    color = SoulMatesOrangeEndColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun PageContent(
    modifier: Modifier = Modifier,
    onboardingData: OnboardingData,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.padding(top = 48.dp),
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = "App Logo"
            )

            Image(
                modifier = Modifier.padding(top = 32.dp),
                painter = painterResource(id = onboardingData.image),
                contentDescription = "Onboarding Image"
            )

            Text(
                modifier = Modifier.padding(top = 24.dp),
                fontSize = 16.sp,
                color = White,
                text = stringResource(id = onboardingData.description),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(
    @PreviewParameter(OnboardingScreenPreviewProvider::class) uiState: UiState,
) {
    OnboardingScreen(
        {}
    )
}