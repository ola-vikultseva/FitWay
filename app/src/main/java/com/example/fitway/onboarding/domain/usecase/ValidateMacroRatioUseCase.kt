package com.example.fitway.onboarding.domain.usecase

import com.example.fitway.onboarding.domain.model.MacroRatio
import com.example.fitway.onboarding.domain.model.validation.MacroRatioValidationResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ValidateMacroRatioUseCase @Inject constructor() {
    operator fun invoke(macroRatio: MacroRatio): MacroRatioValidationResult {
        with(macroRatio) {
            val macroRatioPairs = listOf(
                MacroRatio::proteinRatio to proteinRatio,
                MacroRatio::carbsRatio to carbsRatio,
                MacroRatio::fatsRatio to fatsRatio
            )
            val missingField = macroRatioPairs.firstOrNull { it.second == null }?.first
            if (missingField != null) {
                return MacroRatioValidationResult.Empty(missingField)
            }
            if (proteinRatio!! + carbsRatio!! + fatsRatio!! != 100) {
                return MacroRatioValidationResult.InvalidSum
            }
            return MacroRatioValidationResult.Valid(
                proteinRatio = proteinRatio,
                carbsRatio = carbsRatio,
                fatsRatio = fatsRatio
            )
        }
    }
}