package com.example.fitway.onboarding.domain.usecase

import com.example.fitway.onboarding.domain.model.validation.AgeValidationResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ValidateAgeUseCase @Inject constructor() {
    operator fun invoke(age: Int?) =
        if (age == null) AgeValidationResult.Empty else AgeValidationResult.Valid(age)
}