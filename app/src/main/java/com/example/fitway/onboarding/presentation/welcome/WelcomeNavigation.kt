package com.example.fitway.onboarding.presentation.welcome

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.welcomeScreen(navigateToGenderScreen: () -> Unit) {
    composable(WELCOME_ROUTE) {
        WelcomeScreen(navigateToGenderScreen)
    }
}

const val WELCOME_ROUTE = "welcome"