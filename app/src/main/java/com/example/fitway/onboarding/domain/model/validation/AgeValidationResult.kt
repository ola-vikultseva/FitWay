package com.example.fitway.onboarding.domain.model.validation

sealed class AgeValidationResult {
    data class Valid(val age: Int) : AgeValidationResult()
    data object Empty : AgeValidationResult()
}