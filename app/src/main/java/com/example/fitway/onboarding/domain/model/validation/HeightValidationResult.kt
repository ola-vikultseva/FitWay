package com.example.fitway.onboarding.domain.model.validation

sealed class HeightValidationResult {
    data class Valid(val height: Int) : HeightValidationResult()
    data object Empty : HeightValidationResult()
}