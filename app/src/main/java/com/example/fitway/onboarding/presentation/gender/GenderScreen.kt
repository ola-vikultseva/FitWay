package com.example.fitway.onboarding.presentation.gender

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
import com.example.fitway.onboarding.domain.model.Gender
import com.example.fitway.onboarding.presentation.UiEvent
import com.example.fitway.onboarding.presentation.components.ActionButton
import com.example.fitway.onboarding.presentation.components.SelectableButton

@Composable
fun GenderScreen(
    navigateToAgeScreen: () -> Unit,
    viewModel: GenderViewModel = hiltViewModel()
) {
    LaunchedEffect(true) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                UiEvent.Success -> {
                    navigateToAgeScreen()
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
                text = stringResource(R.string.whats_your_gender),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(Modifier.height(16.dp))
            Row {
                val selectedGender = viewModel.selectedGender.value
                SelectableButton(
                    text = stringResource(R.string.male),
                    isSelected = selectedGender == Gender.MALE,
                    onClick = {
                        viewModel.onGenderClick(Gender.MALE)
                    }
                )
                Spacer(Modifier.width(16.dp))
                SelectableButton(
                    text = stringResource(R.string.female),
                    isSelected = selectedGender == Gender.FEMALE,
                    onClick = {
                        viewModel.onGenderClick(Gender.FEMALE)
                    }
                )
            }
        }
        ActionButton(
            text = stringResource(R.string.next),
            onClick = viewModel::saveGenderAndContinue,
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}
