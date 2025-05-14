package com.example.fitway.onboarding.presentation.weight_goal

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.weightGoalScreen(
    navigateToMacroRatioScreen: () -> Unit
) {
    composable(WEIGHT_GOAL_ROUTE) {
        WeightGoalScreen(navigateToMacroRatioScreen)
    }
}

fun NavController.navigateToWeightGoalScreen() {
    navigate(WEIGHT_GOAL_ROUTE)
}

private const val WEIGHT_GOAL_ROUTE = "weight_goal"