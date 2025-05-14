package com.example.fitway.core.util

import android.content.Context

sealed class UiText {
    data class StringResWithResArgs(val resId: Int, val argResIds: List<Int>) : UiText()
    data class StringRes(val resId: Int, val args: List<Any> = emptyList()) : UiText()
    data class DynamicString(val value: String) : UiText()

    fun asString(context: Context): String = when (this) {
        is StringResWithResArgs -> {
            val resolvedArgs = argResIds.map { context.getString(it) }
            context.getString(resId, *resolvedArgs.toTypedArray())
        }
        is StringRes -> {
            context.getString(resId, *args.toTypedArray())
        }
        is DynamicString -> {
            value
        }
    }
}