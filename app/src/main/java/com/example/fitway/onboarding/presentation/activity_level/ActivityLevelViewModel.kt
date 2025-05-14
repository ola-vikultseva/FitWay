package com.example.fitway.onboarding.presentation.activity_level

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitway.onboarding.domain.UserPreferences
import com.example.fitway.onboarding.domain.model.ActivityLevel
import com.example.fitway.onboarding.presentation.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityLevelViewModel @Inject constructor(
    private val userPreferences: UserPreferences
) : ViewModel() {

    var selectedActivityLevel = mutableStateOf(ActivityLevel.MEDIUM)
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onActivityLevelClick(activityLevel: ActivityLevel) {
        selectedActivityLevel.value = activityLevel
    }

    fun saveActivityLevelAndContinue() {
        viewModelScope.launch {
            userPreferences.saveActivityLevel(selectedActivityLevel.value)
            _uiEvent.send(UiEvent.Success)
        }
    }
}