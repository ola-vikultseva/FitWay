package com.example.fitway.onboarding.presentation.age

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitway.R
import com.example.fitway.core.util.UiText
import com.example.fitway.core.util.filterDigitsAndLimit
import com.example.fitway.onboarding.domain.UserPreferences
import com.example.fitway.onboarding.domain.model.validation.AgeValidationResult
import com.example.fitway.onboarding.domain.usecase.ValidateAgeUseCase
import com.example.fitway.onboarding.presentation.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgeViewModel @Inject constructor(
    private val validateAgeUseCase: ValidateAgeUseCase,
    private val userPreferences: UserPreferences
) : ViewModel() {

    var age = mutableStateOf("30")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onAgeChange(input: String) {
        val filteredInput = input.filterDigitsAndLimit(3)
        age.value = filteredInput
    }

    fun saveAgeAndContinue() {
        viewModelScope.launch {
            when (val validationResult = validateAgeUseCase(age.value.toIntOrNull())) {
                AgeValidationResult.Empty -> {
                    _uiEvent.send(UiEvent.Message(UiText.StringRes(R.string.age_cant_be_empty)))
                }
                is AgeValidationResult.Valid -> {
                    userPreferences.saveAge(validationResult.age)
                    _uiEvent.send(UiEvent.Success)
                }
            }
        }
    }
}