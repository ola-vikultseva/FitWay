package com.example.fitway.onboarding.domain.model

data class UserInfo(
    val gender: Gender,
    val age: Int,
    val height: Int,
    val weight: Int,
    val weightGoal: WeightGoal,
    val activityLevel: ActivityLevel,
    val carbRatio: Int,
    val proteinRatio: Int,
    val fatRatio: Int
)
