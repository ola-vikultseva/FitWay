package com.example.fitway.onboarding.presentation.height

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitway.R
import com.example.fitway.core.util.UiText
import com.example.fitway.core.util.filterDigitsAndLimit
import com.example.fitway.onboarding.domain.UserPreferences
import com.example.fitway.onboarding.domain.model.validation.HeightValidationResult
import com.example.fitway.onboarding.domain.usecase.ValidateHeightUseCase
import com.example.fitway.onboarding.presentation.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeightViewModel @Inject constructor(
    private val validateHeightUseCase: ValidateHeightUseCase,
    private val userPreferences: UserPreferences
) : ViewModel() {

    var height = mutableStateOf("180")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onHeightChange(input: String) {
        val filteredInput = input.filterDigitsAndLimit(3)
        height.value = filteredInput
    }

    fun saveHeightAndContinue() {
        viewModelScope.launch {
            when (val validationResult = validateHeightUseCase(height.value.toIntOrNull())) {
                HeightValidationResult.Empty -> {
                    _uiEvent.send(UiEvent.Message(UiText.StringRes(R.string.height_cant_be_empty)))
                }
                is HeightValidationResult.Valid -> {
                    userPreferences.saveHeight(validationResult.height)
                    _uiEvent.send(UiEvent.Success)
                }
            }
        }
    }
}