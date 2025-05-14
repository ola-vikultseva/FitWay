package com.example.fitway.onboarding.presentation.macro_ratio

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitway.R
import com.example.fitway.core.util.UiText
import com.example.fitway.core.util.filterDigitsAndLimit
import com.example.fitway.onboarding.domain.UserPreferences
import com.example.fitway.onboarding.domain.model.MacroRatio
import com.example.fitway.onboarding.domain.model.validation.MacroRatioValidationResult
import com.example.fitway.onboarding.domain.usecase.ValidateMacroRatioUseCase
import com.example.fitway.onboarding.presentation.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.reflect.KProperty1

@HiltViewModel
class MacroRationViewModel @Inject constructor(
    private val validateMacroRatioUseCase: ValidateMacroRatioUseCase,
    private val userPreferences: UserPreferences
) : ViewModel() {

    var macroRatioUiState =
        mutableStateOf(MacroRatioUiState(proteinRatio = "30", carbsRatio = "40", fatsRatio = "30"))
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onProteinRatioChange(input: String) {
        val filteredInput = input.filterDigitsAndLimit(3)
        macroRatioUiState.value = macroRatioUiState.value.copy(proteinRatio = filteredInput)
    }

    fun onCarbsRatioChange(input: String) {
        val filteredInput = input.filterDigitsAndLimit(3)
        macroRatioUiState.value = macroRatioUiState.value.copy(carbsRatio = filteredInput)
    }

    fun onFatsRatioChange(input: String) {
        val filteredInput = input.filterDigitsAndLimit(3)
        macroRatioUiState.value = macroRatioUiState.value.copy(fatsRatio = filteredInput)
    }

    fun saveMacroRatioAndContinue() {
        viewModelScope.launch {
            val (proteinRatio, carbsRatio, fatsRatio) = macroRatioUiState.value
            when (val validationResult = validateMacroRatioUseCase(
                MacroRatio(
                    proteinRatio = proteinRatio.toIntOrNull(),
                    carbsRatio = carbsRatio.toIntOrNull(),
                    fatsRatio = fatsRatio.toIntOrNull()
                )
            )) {
                is MacroRatioValidationResult.Empty -> {
                    _uiEvent.send(
                        UiEvent.Message(
                            UiText.StringResWithResArgs(
                                resId = R.string.macro_ratio_cant_be_empty,
                                argResIds = listOf(getMacroNameResId(validationResult.missingField))
                            )
                        )
                    )
                }
                MacroRatioValidationResult.InvalidSum -> {
                    _uiEvent.send(UiEvent.Message(UiText.StringRes(R.string.macro_ratio_invalid_sum)))
                }
                is MacroRatioValidationResult.Valid -> {
                    with(userPreferences) {
                        saveProteinRatio(validationResult.proteinRatio)
                        saveCarbsRatio(validationResult.carbsRatio)
                        saveFatsRatio(validationResult.fatsRatio)
                    }
                    _uiEvent.send(UiEvent.Success)
                }
            }
        }
    }

    private fun getMacroNameResId(field: KProperty1<MacroRatio, Int?>): Int =
        when (field) {
        MacroRatio::proteinRatio -> R.string.protein
        MacroRatio::carbsRatio -> R.string.carbs
        MacroRatio::fatsRatio -> R.string.fats
        else -> error("Unknown field passed: $field")
    }
}