package com.example.fitway.onboarding.presentation.activity_level

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.activityLevelScreen(
    navigateToWeightGoalScreen: () -> Unit
) {
    composable(ACTIVITY_LEVEL_ROUTE) {
        ActivityLevelScreen(navigateToWeightGoalScreen)
    }
}

fun NavController.navigateToActivityLevelScreen() {
    navigate(ACTIVITY_LEVEL_ROUTE)
}

private const val ACTIVITY_LEVEL_ROUTE = "activity_level"