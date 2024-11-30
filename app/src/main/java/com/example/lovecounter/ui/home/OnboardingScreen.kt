package com.example.lovecounter.ui.home

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.lovecounter.R
import com.example.lovecounter.ui.home.OnboardingContract.UiState
import com.example.lovecounter.ui.theme.SoulMatesOrangeEndColor
import com.example.lovecounter.ui.theme.SoulMatesOrangeStartColor
import com.example.lovecounter.ui.theme.TransparentWhite
import com.example.lovecounter.ui.theme.White
import com.example.lovecounter.ui.theme.indicatorSelectColor
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(
    onFinishClicked: () -> Unit,
    onSkipClicked: () -> Unit
    /* uiState: UiState,
    uiEffect: Flow<OnboardingContract.UiEffect>,
    onAction: (OnboardingContract.UiAction) -> Unit */
) {
    val onboardingPages = getOnboardingData()

    val pagerState =
        rememberPagerState(initialPage = 0, initialPageOffsetFraction = 0f) { onboardingPages.size }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(SoulMatesOrangeStartColor, SoulMatesOrangeEndColor)
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Button(
            modifier = Modifier
                .align(Alignment.End)
                .padding(top = 24.dp, end = 24.dp)
                .width(130.dp),
            colors = ButtonDefaults.buttonColors(containerColor = TransparentWhite),
            shape = RoundedCornerShape(12.dp),
            onClick = onSkipClicked
        ) {
            Text(text = stringResource(R.string.skip))
        }



        HorizontalPager(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            pageSize = PageSize.Fill,
            state = pagerState
        ) { page ->
            PageContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                onboardingPages[page]
            )
        }

        Spacer(modifier = Modifier.size(12.dp))

        Indicators(
            size = onboardingPages.size,
            index = pagerState.currentPage
        )

        Spacer(modifier = Modifier.size(12.dp))

        ButtonContent(
            isLastPage = pagerState.currentPage == onboardingPages.size - 1,
            isFirstPage = pagerState.currentPage == 0,
            onBackClicked = {
                if (pagerState.currentPage == 0) {
                    //finish()
                } else {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                    }
                }
            },
            onNextClicked = {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            },
            onFinishClicked = onFinishClicked
        )
    }
}

@Composable
fun PageContent(
    modifier: Modifier = Modifier,
    onboardingData: OnboardingData
) {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(onboardingData.image))
    val progress by animateLottieCompositionAsState(composition)

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LottieAnimation(
            modifier = Modifier
                .size(250.dp),
            composition = composition,
            progress = { progress }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            fontSize = 16.sp,
            color = White,
            text = stringResource(id = onboardingData.description),
        )
    }
}

@Composable
fun Indicators(modifier: Modifier = Modifier, size: Int, index: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
    ) {
        repeat(size) {
            Box(
                modifier = Modifier
                    .size(if (it == index) 10.dp else 8.dp)
                    .clip(shape = CircleShape)
                    .background(
                        if (it == index) indicatorSelectColor else TransparentWhite
                    )
            )
        }
    }
}

@Composable
fun ButtonContent(
    isLastPage: Boolean,
    isFirstPage: Boolean,
    onBackClicked: () -> Unit,
    onNextClicked: () -> Unit,
    onFinishClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp)
    ) {
        if (!isFirstPage) {
            Button(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .width(130.dp),
                colors = ButtonDefaults.buttonColors(containerColor = TransparentWhite),
                shape = RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp),
                onClick = onBackClicked
            ) {
                Text(text = stringResource(R.string.back))
            }
        }

        Button(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .width(130.dp),
            colors = ButtonDefaults.buttonColors(containerColor = TransparentWhite),
            shape = RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp),
            onClick = {
                if (isLastPage) {
                    onFinishClicked()
                } else {
                    onNextClicked()
                }
            }
        ) {
            if (isLastPage) {
                Text(text = stringResource(R.string.finish))
            } else {
                Text(text = stringResource(R.string.next))
            }
        }
    }
}

@Preview
@Composable
fun PreviewOnboardingScreen() {
    OnboardingScreen(
        onFinishClicked = {},
        onSkipClicked = {}
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(
    @PreviewParameter(OnboardingScreenPreviewProvider::class) uiState: UiState,
) {
    OnboardingScreen(
        {}, {}
        /* uiState = uiState,
        uiEffect = emptyFlow(),
        onAction = {}, */
    )
}