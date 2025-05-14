package com.example.fitway.onboarding.presentation.weight

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitway.R
import com.example.fitway.core.util.UiText
import com.example.fitway.core.util.filterDigitsAndLimit
import com.example.fitway.onboarding.domain.UserPreferences
import com.example.fitway.onboarding.domain.model.validation.WeightValidationResult
import com.example.fitway.onboarding.domain.usecase.ValidateWeightUseCase
import com.example.fitway.onboarding.presentation.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeightViewModel @Inject constructor(
    private val validateWeightUseCase: ValidateWeightUseCase,
    private val userPreferences: UserPreferences
) : ViewModel() {

    var weight = mutableStateOf("80")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onWeightChange(input: String) {
        val filteredInput = input.filterDigitsAndLimit(3)
        weight.value = filteredInput
    }

    fun saveWeightAndContinue() {
        viewModelScope.launch {
            when (val validationResult = validateWeightUseCase(weight.value.toIntOrNull())) {
                WeightValidationResult.Empty -> {
                    _uiEvent.send(UiEvent.Message(UiText.StringRes(R.string.weight_cant_be_empty)))
                }
                is WeightValidationResult.Valid -> {
                    userPreferences.saveWeight(validationResult.weight)
                    _uiEvent.send(UiEvent.Success)
                }
            }
        }
    }
}