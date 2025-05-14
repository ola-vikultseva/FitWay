package com.example.fitway.onboarding.presentation.components

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UnitTextField(
    value: String,
    onValueChange: (String) -> Unit,
    unit: String
) {
    Row {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            singleLine = true,
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.primary,
                fontSize = 70.sp
            ),
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .alignByBaseline()
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = unit,
            modifier = Modifier
                .alignByBaseline()
        )
    }
}