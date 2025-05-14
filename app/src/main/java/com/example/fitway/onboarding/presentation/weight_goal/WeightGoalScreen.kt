package com.example.fitway.onboarding.presentation.weight_goal

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
import com.example.fitway.onboarding.domain.model.WeightGoal
import com.example.fitway.onboarding.presentation.UiEvent
import com.example.fitway.onboarding.presentation.components.ActionButton
import com.example.fitway.onboarding.presentation.components.SelectableButton

@Composable
fun WeightGoalScreen(
    navigateToMacroRatioScreen: () -> Unit,
    viewModel: WeightGoalViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                UiEvent.Success -> {
                    navigateToMacroRatioScreen()
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
                text = stringResource(R.string.whats_your_weight_goal),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(Modifier.height(16.dp))
            Row {
                val selectedWeightGoal = viewModel.selectedWeightGoal.value
                SelectableButton(
                    text = stringResource(R.string.lose_weight_goal),
                    isSelected = selectedWeightGoal == WeightGoal.LOSE,
                    onClick = {
                        viewModel.onWeightGoalClick(WeightGoal.LOSE)
                    }
                )
                Spacer(Modifier.width(16.dp))
                SelectableButton(
                    text = stringResource(R.string.keep_weight_goal),
                    isSelected = selectedWeightGoal == WeightGoal.KEEP,
                    onClick = {
                        viewModel.onWeightGoalClick(WeightGoal.KEEP)
                    }
                )
                Spacer(Modifier.width(16.dp))
                SelectableButton(
                    text = stringResource(R.string.gain_weight_goal),
                    isSelected = selectedWeightGoal == WeightGoal.GAIN,
                    onClick = {
                        viewModel.onWeightGoalClick(WeightGoal.GAIN)
                    }
                )
            }
        }
        ActionButton(
            text = stringResource(R.string.next),
            onClick = viewModel::saveWeightGoalAndContinue,
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}