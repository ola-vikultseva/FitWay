package com.example.fitway.onboarding.presentation.height

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.heightScreen(
    snackbarHostState: SnackbarHostState,
    navigateToWeightScreen: () -> Unit
) {
    composable(HEIGHT_ROUTE) {
        HeightScreen(
            snackbarHostState = snackbarHostState,
            navigateToWeightScreen = navigateToWeightScreen
        )
    }
}

fun NavController.navigateToHeightScreen() {
    navigate(HEIGHT_ROUTE)
}

private const val HEIGHT_ROUTE = "height"