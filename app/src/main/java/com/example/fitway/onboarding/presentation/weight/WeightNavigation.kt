package com.example.fitway.onboarding.presentation.weight

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.weightScreen(
    snackbarHostState: SnackbarHostState,
    navigateToActivityLevelScreen: () -> Unit
) {
    composable(WEIGHT_ROUTE) {
        WeightScreen(
            snackbarHostState = snackbarHostState,
            navigateToActivityLevelScreen = navigateToActivityLevelScreen
        )
    }
}

fun NavController.navigateToWeightScreen() {
    navigate(WEIGHT_ROUTE)
}

private const val WEIGHT_ROUTE = "weight"