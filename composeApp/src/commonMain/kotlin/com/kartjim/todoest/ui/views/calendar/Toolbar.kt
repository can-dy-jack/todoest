package com.kartjim.todoest.ui.views.calendar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.CalendarViewDay
import androidx.compose.material.icons.filled.CalendarViewMonth
import androidx.compose.material.icons.filled.Today
import androidx.compose.material.icons.filled.ViewDay
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable

@Composable
fun ToolBar() {
    Row {
        Box {
            Icon(
                Icons.Default.CalendarViewMonth,
                contentDescription = "month-view"
            )
        }

        Box {
            Icon(
                Icons.Default.CalendarViewDay,
                contentDescription = "day-view"
            )
        }
    }
}