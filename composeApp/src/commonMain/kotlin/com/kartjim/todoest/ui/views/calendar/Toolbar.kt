package com.kartjim.todoest.ui.views.calendar

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarViewDay
import androidx.compose.material.icons.filled.CalendarViewMonth
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun ToolBar(
    current: CalendarViewType = CalendarViewType.MONTH,
    onChange: (CalendarViewType) -> Unit = {}
) {
    IconItem(
        Icons.Default.CalendarViewMonth,
        CalendarViewType.MONTH,
        current == CalendarViewType.MONTH,
        "month-view",
        onClick = {
            onChange(CalendarViewType.MONTH)
        }
    )
    IconItem(
        Icons.Default.CalendarViewDay,
        CalendarViewType.WEEK,
        current == CalendarViewType.WEEK,
        "day-view",
        onClick = {
            onChange(CalendarViewType.WEEK)
        }
    )
    IconItem(
        Icons.Default.LocalFireDepartment,
        CalendarViewType.HEATMAP,
        current == CalendarViewType.HEATMAP,
        "heatmap-view",
        onClick = {
            onChange(CalendarViewType.HEATMAP)
        }
    )
}

@Composable
fun IconItem(
    icon: ImageVector,
    type: CalendarViewType,
    isActive: Boolean,
    desc: String = "",
    onClick: (type: CalendarViewType) -> Unit = {}
) {
    Box {
        IconButton(
            onClick = {
                onClick(type)
            },
        ) {
            Icon(
                icon,
                contentDescription = desc,
                tint = if(isActive) MaterialTheme.colorScheme.primary else Color.Gray,
            )
        }
    }
}
