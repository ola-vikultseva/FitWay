package com.example.fitway.onboarding.presentation.gender

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitway.onboarding.domain.UserPreferences
import com.example.fitway.onboarding.domain.model.Gender
import com.example.fitway.onboarding.presentation.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenderViewModel @Inject constructor(
    private val userPreferences: UserPreferences
) : ViewModel() {

    var selectedGender = mutableStateOf(Gender.MALE)
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onGenderClick(gender: Gender) {
        selectedGender.value = gender
    }

    fun saveGenderAndContinue() {
        viewModelScope.launch {
            userPreferences.saveGender(selectedGender.value)
            _uiEvent.send(UiEvent.Success)
        }
    }
}