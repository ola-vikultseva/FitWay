package com.example.fitway.onboarding.domain.usecase

import com.example.fitway.onboarding.domain.model.validation.HeightValidationResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ValidateHeightUseCase @Inject constructor() {
    operator fun invoke(height: Int?) =
        if (height == null) HeightValidationResult.Empty else HeightValidationResult.Valid(height)
}