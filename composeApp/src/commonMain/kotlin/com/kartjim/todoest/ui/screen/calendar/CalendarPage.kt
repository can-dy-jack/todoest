package com.kartjim.todoest.ui.screen.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.moriafly.salt.ui.Text

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarPage () {
    val datePickerState = rememberDatePickerState()

    Column {
        DatePicker(
            state = datePickerState,
            showModeToggle = false,
            title = {}
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Text("xxx")
        }
    }
}
