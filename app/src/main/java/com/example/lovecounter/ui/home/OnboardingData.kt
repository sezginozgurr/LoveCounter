package com.example.lovecounter.ui.home

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import com.example.lovecounter.R

data class OnboardingData(
    @StringRes val title: Int,
    @StringRes val description: Int,
    val image: Int
)

@SuppressLint("ResourceType")
fun getOnboardingData() = listOf(
    OnboardingData(
        R.string.onboarding_title_1,
        R.string.onboarding_description_1,
        R.raw.shopping
    ),
    OnboardingData(
        R.string.onboarding_title_2,
        R.string.onboarding_description_2,
        R.raw.shopping
    ),
    OnboardingData(
        R.string.onboarding_title_3,
        R.string.onboarding_description_3,
        R.raw.shopping
    )
)