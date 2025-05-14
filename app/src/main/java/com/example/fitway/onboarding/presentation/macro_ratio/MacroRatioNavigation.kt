package com.example.fitway.onboarding.presentation.macro_ratio

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.macroRatioScreen(
    snackbarHostState: SnackbarHostState,
    navigateToNextScreen: () -> Unit
) {
    composable(MACRO_RATIO_ROUTE) {
        MacroRatioScreen(
            snackbarHostState = snackbarHostState,
            navigateToNextScreen = navigateToNextScreen
        )
    }
}

fun NavController.navigateToMacroRatioScreen() {
    navigate(MACRO_RATIO_ROUTE)
}

private const val MACRO_RATIO_ROUTE = "macro_ratio"