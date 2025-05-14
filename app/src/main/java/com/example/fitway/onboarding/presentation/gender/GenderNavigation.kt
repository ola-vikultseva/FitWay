package com.example.fitway.onboarding.presentation.gender

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.genderScreen(navigateToAgeScreen: () -> Unit) {
    composable(GENDER_ROUTE) {
        GenderScreen(navigateToAgeScreen)
    }
}

fun NavController.navigateToGenderScreen() {
    navigate(GENDER_ROUTE)
}

private const val GENDER_ROUTE = "gender"