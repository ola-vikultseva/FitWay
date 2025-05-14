package com.example.fitway.onboarding.data

import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.fitway.onboarding.domain.UserPreferences
import com.example.fitway.onboarding.domain.model.ActivityLevel
import com.example.fitway.onboarding.domain.model.Gender
import com.example.fitway.onboarding.domain.model.UserInfo
import com.example.fitway.onboarding.domain.model.WeightGoal
import javax.inject.Inject

class UserPreferencesImpl @Inject constructor(
    private val sharedPrefs: SharedPreferences
) : UserPreferences {

    override fun saveGender(gender: Gender) {
        sharedPrefs.edit() {
            putString(KEY_GENDER, gender.name)
        }
    }

    override fun saveAge(age: Int) {
        sharedPrefs.edit() {
            putInt(KEY_AGE, age)
        }
    }

    override fun saveHeight(height: Int) {
        sharedPrefs.edit() {
            putInt(KEY_HEIGHT, height)
        }
    }

    override fun saveWeight(weight: Int) {
        sharedPrefs.edit() {
            putInt(KEY_WEIGHT, weight)
        }
    }

    override fun saveWeightGoal(weightGoal: WeightGoal) {
        sharedPrefs.edit() {
            putString(KEY_WEIGHT_GOAL, weightGoal.name)
        }
    }

    override fun saveActivityLevel(activityLevel: ActivityLevel) {
        sharedPrefs.edit() {
            putString(KEY_ACTIVITY_LEVEL, activityLevel.name)
        }
    }

    override fun saveCarbsRatio(carbsRatio: Int) {
        sharedPrefs.edit() {
            putInt(KEY_CARBS_RATIO, carbsRatio)
        }
    }

    override fun saveProteinRatio(proteinRatio: Int) {
        sharedPrefs.edit() {
            putInt(KEY_PROTEIN_RATIO, proteinRatio)
        }
    }

    override fun saveFatsRatio(fatsRatio: Int) {
        sharedPrefs.edit() {
            putInt(KEY_FATS_RATIO, fatsRatio)
        }
    }

    override fun getUserInfo(): UserInfo {
        val genderString = sharedPrefs.getString(KEY_GENDER, null)
        val age = sharedPrefs.getInt(KEY_AGE, -1)
        val height = sharedPrefs.getInt(KEY_HEIGHT, -1)
        val weight = sharedPrefs.getInt(KEY_WEIGHT, -1)
        val weightGoalString = sharedPrefs.getString(KEY_WEIGHT_GOAL, null)
        val activityLevelString = sharedPrefs.getString(KEY_ACTIVITY_LEVEL, null)
        val carbRatio = sharedPrefs.getInt(KEY_CARBS_RATIO, -1)
        val proteinRatio = sharedPrefs.getInt(KEY_PROTEIN_RATIO, -1)
        val fatRatio = sharedPrefs.getInt(KEY_FATS_RATIO, -1)
        return UserInfo(
            gender = Gender.valueOf(genderString ?: Gender.MALE.name),
            age = age,
            height = height,
            weight = weight,
            weightGoal = WeightGoal.valueOf(weightGoalString ?: WeightGoal.KEEP.name),
            activityLevel = ActivityLevel.valueOf(activityLevelString ?: ActivityLevel.MEDIUM.name),
            carbRatio = carbRatio,
            proteinRatio = proteinRatio,
            fatRatio = fatRatio
        )
    }

    private companion object {
        const val KEY_GENDER = "key_gender"
        const val KEY_AGE = "key_age"
        const val KEY_HEIGHT = "key_height"
        const val KEY_WEIGHT = "key_weight"
        const val KEY_WEIGHT_GOAL = "key_weight_goal"
        const val KEY_ACTIVITY_LEVEL = "key_activity_level"
        const val KEY_CARBS_RATIO = "key_carbs_ratio"
        const val KEY_PROTEIN_RATIO = "key_protein_ratio"
        const val KEY_FATS_RATIO = "key_fats_ratio"
    }
}