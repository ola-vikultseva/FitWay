package com.example.fitway.onboarding.presentation.age

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.ageScreen(
    snackbarHostState: SnackbarHostState,
    navigateToHeightScreen: () -> Unit
) {
    composable(AGE_ROUTE) {
        AgeScreen(
            snackbarHostState = snackbarHostState,
            navigateToHeightScreen = navigateToHeightScreen
        )
    }
}

fun NavController.navigateToAgeScreen() {
    navigate(AGE_ROUTE)
}

private const val AGE_ROUTE = "age"