package com.example.fitway

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.fitway.onboarding.presentation.activity_level.activityLevelScreen
import com.example.fitway.onboarding.presentation.activity_level.navigateToActivityLevelScreen
import com.example.fitway.onboarding.presentation.age.ageScreen
import com.example.fitway.onboarding.presentation.age.navigateToAgeScreen
import com.example.fitway.onboarding.presentation.gender.genderScreen
import com.example.fitway.onboarding.presentation.gender.navigateToGenderScreen
import com.example.fitway.onboarding.presentation.height.heightScreen
import com.example.fitway.onboarding.presentation.height.navigateToHeightScreen
import com.example.fitway.onboarding.presentation.macro_ratio.macroRatioScreen
import com.example.fitway.onboarding.presentation.macro_ratio.navigateToMacroRatioScreen
import com.example.fitway.onboarding.presentation.weight.navigateToWeightScreen
import com.example.fitway.onboarding.presentation.weight.weightScreen
import com.example.fitway.onboarding.presentation.weight_goal.navigateToWeightGoalScreen
import com.example.fitway.onboarding.presentation.weight_goal.weightGoalScreen
import com.example.fitway.onboarding.presentation.welcome.WELCOME_ROUTE
import com.example.fitway.onboarding.presentation.welcome.welcomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val snackbarHostState = remember { SnackbarHostState() }
            Scaffold(
                snackbarHost = {
                    SnackbarHost(snackbarHostState)
                }
            ) { paddingValues ->
                NavHost(
                    modifier = Modifier.padding(paddingValues),
                    navController = navController,
                    startDestination = WELCOME_ROUTE
                ) {
                    welcomeScreen(navController::navigateToGenderScreen)
                    genderScreen(navController::navigateToAgeScreen)
                    ageScreen(
                        snackbarHostState = snackbarHostState,
                        navigateToHeightScreen = navController::navigateToHeightScreen
                    )
                    heightScreen(
                        snackbarHostState = snackbarHostState,
                        navigateToWeightScreen = navController::navigateToWeightScreen
                    )
                    weightScreen(
                        snackbarHostState = snackbarHostState,
                        navigateToActivityLevelScreen = navController::navigateToActivityLevelScreen
                    )
                    activityLevelScreen(navController::navigateToWeightGoalScreen)
                    weightGoalScreen(navController::navigateToMacroRatioScreen)
                    macroRatioScreen(
                        snackbarHostState = snackbarHostState,
                        navigateToNextScreen = { }
                    )
                }
            }
        }
    }
}