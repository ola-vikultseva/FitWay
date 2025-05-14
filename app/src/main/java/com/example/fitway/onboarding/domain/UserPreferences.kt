package com.example.fitway.onboarding.domain

import com.example.fitway.onboarding.domain.model.ActivityLevel
import com.example.fitway.onboarding.domain.model.Gender
import com.example.fitway.onboarding.domain.model.UserInfo
import com.example.fitway.onboarding.domain.model.WeightGoal

interface UserPreferences {
    fun saveGender(gender: Gender)
    fun saveAge(age: Int)
    fun saveHeight(height: Int)
    fun saveWeight(weight: Int)
    fun saveWeightGoal(weightGoal: WeightGoal)
    fun saveActivityLevel(activityLevel: ActivityLevel)
    fun saveCarbsRatio(carbsRatio: Int)
    fun saveProteinRatio(proteinRatio: Int)
    fun saveFatsRatio(fatsRatio: Int)
    fun getUserInfo(): UserInfo
}