package com.example.fitway.onboarding.presentation.activity_level

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitway.R
import com.example.fitway.onboarding.domain.model.ActivityLevel
import com.example.fitway.onboarding.presentation.UiEvent
import com.example.fitway.onboarding.presentation.components.ActionButton
import com.example.fitway.onboarding.presentation.components.SelectableButton

@Composable
fun ActivityLevelScreen(
    navigateToWeightGoalScreen: () -> Unit,
    viewModel: ActivityLevelViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                UiEvent.Success -> {
                    navigateToWeightGoalScreen()
                }
                else -> Unit
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.whats_your_activity_level),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(Modifier.height(16.dp))
            Row {
                val selectedActivityLevel = viewModel.selectedActivityLevel.value
                SelectableButton(
                    text = stringResource(R.string.low_activity_level),
                    isSelected = selectedActivityLevel == ActivityLevel.LOW,
                    onClick = {
                        viewModel.onActivityLevelClick(ActivityLevel.LOW)
                    }
                )
                Spacer(Modifier.width(16.dp))
                SelectableButton(
                    text = stringResource(R.string.medium_activity_level),
                    isSelected = selectedActivityLevel == ActivityLevel.MEDIUM,
                    onClick = {
                        viewModel.onActivityLevelClick(ActivityLevel.MEDIUM)
                    }
                )
                Spacer(Modifier.width(16.dp))
                SelectableButton(
                    text = stringResource(R.string.high_activity_level),
                    isSelected = selectedActivityLevel == ActivityLevel.HIGH,
                    onClick = {
                        viewModel.onActivityLevelClick(ActivityLevel.HIGH)
                    }
                )
            }
        }
        ActionButton(
            text = stringResource(R.string.next),
            onClick = viewModel::saveActivityLevelAndContinue,
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}