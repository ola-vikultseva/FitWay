package com.example.fitway.onboarding.presentation.height

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitway.R
import com.example.fitway.onboarding.presentation.UiEvent
import com.example.fitway.onboarding.presentation.components.ActionButton
import com.example.fitway.onboarding.presentation.components.UnitTextField

@Composable
fun HeightScreen(
    snackbarHostState: SnackbarHostState,
    navigateToWeightScreen: () -> Unit,
    viewModel: HeightViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is UiEvent.Message -> {
                    snackbarHostState.showSnackbar(uiEvent.uiText.asString(context))
                }
                UiEvent.Success -> {
                    navigateToWeightScreen()
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
                text = stringResource(R.string.whats_your_height),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(Modifier.height(16.dp))
            UnitTextField(
                value = viewModel.height.value,
                onValueChange = { value ->
                    viewModel.onHeightChange(value)
                },
                unit = stringResource(R.string.cm)
            )
        }
        ActionButton(
            text = stringResource(R.string.next),
            onClick = viewModel::saveHeightAndContinue,
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}