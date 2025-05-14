package com.example.fitway.onboarding.presentation.weight_goal

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitway.onboarding.domain.UserPreferences
import com.example.fitway.onboarding.domain.model.WeightGoal
import com.example.fitway.onboarding.presentation.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeightGoalViewModel @Inject constructor(
    private val userPreferences: UserPreferences
) : ViewModel() {

    var selectedWeightGoal = mutableStateOf(WeightGoal.KEEP)
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onWeightGoalClick(weightGoal: WeightGoal) {
        selectedWeightGoal.value = weightGoal
    }

    fun saveWeightGoalAndContinue() {
        viewModelScope.launch {
            userPreferences.saveWeightGoal(selectedWeightGoal.value)
            _uiEvent.send(UiEvent.Success)
        }
    }
}