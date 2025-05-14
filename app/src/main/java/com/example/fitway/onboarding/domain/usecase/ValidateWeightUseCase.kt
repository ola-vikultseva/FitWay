package com.example.fitway.onboarding.domain.usecase

import com.example.fitway.onboarding.domain.model.validation.WeightValidationResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ValidateWeightUseCase @Inject constructor() {
    operator fun invoke(weight: Int?) =
        if (weight == null) WeightValidationResult.Empty else WeightValidationResult.Valid(weight)
}