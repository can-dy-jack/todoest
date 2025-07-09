package com.kartjim.todoest.ui.screen.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import com.moriafly.salt.ui.Text

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarPage () {
    val datePickerState = rememberDatePickerState()

    Column {
        DatePicker(
            state = datePickerState,
            showModeToggle = false
        )
    }
}
