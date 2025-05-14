package com.example.fitway.onboarding.domain.model.validation

sealed class WeightValidationResult {
    data class Valid(val weight: Int) : WeightValidationResult()
    data object Empty : WeightValidationResult()
}