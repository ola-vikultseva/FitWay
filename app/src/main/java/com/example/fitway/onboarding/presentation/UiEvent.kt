package com.example.fitway.onboarding.presentation

import com.example.fitway.core.util.UiText

sealed class UiEvent {
    data object Success : UiEvent()
    data class Error(val uiText: UiText) : UiEvent()
    data class Message(val uiText: UiText) : UiEvent()
}