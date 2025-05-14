package com.example.fitway.core.util

fun String.filterDigitsAndLimit(maxLength: Int) = this.filter { it.isDigit() }.take(maxLength)