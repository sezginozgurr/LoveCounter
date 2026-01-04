package com.example.lovecounter.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.lovecounter.R

internal val LocalIcons = staticCompositionLocalOf { LCIcons() }

class LCIcons {
    val appLogo: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.app_logo)

    val camera: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.ic_camera)

    val chooseDate: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.ic_choose_date)

    val gift: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.ic_gift)

    val heart: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.ic_heart)

    val profileBlack: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.ic_profile_black)

    val profileOrange: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.ic_profile_orange)

    val profileRed: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.ic_profile_red)

    val profileYellow: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.ic_profile_yellow)

    val topMemories: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.ic_top_memories)

    val cat: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.ic_cat)

    val onboardingFirst: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.onboarding_first)

    val onboardingSecond: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.onboarding_second)

    val onboardingThird: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.onboarding_third)

    val specialDayOne: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.special_day_1)

    val specialDayTwo: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.special_day_2)

    val specialDayThree: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.special_day_3)

    val specialDayFour: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.special_day_4)

    val tabOne: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.tab_1)

    val tabTwo: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.tab_2)

    val tabThree: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.tab_3)

    val tabFour: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.tab_4)

    val whiteBg: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.white_top_bg)

    val yearBgFirstly: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.year_bg_firstly)

    val yearBgSecondly: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.year_bg_secondly)

    val defaultMalePhoto: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.home_default_profile_male)

    val defaultFemalePhoto: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.home_default_profile_female)

    val fakePhoto: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.fakephoto)
}