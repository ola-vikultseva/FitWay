package com.example.fitway.onboarding.domain.model.validation

import com.example.fitway.onboarding.domain.model.MacroRatio
import kotlin.reflect.KProperty1

sealed class MacroRatioValidationResult {
    data class Valid(
        val proteinRatio: Int,
        val carbsRatio: Int,
        val fatsRatio: Int
    ) : MacroRatioValidationResult()
    data class Empty(val missingField: KProperty1<MacroRatio, Int?>) : MacroRatioValidationResult()
    data object InvalidSum : MacroRatioValidationResult()
}