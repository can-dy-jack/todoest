package com.kartjim.todoest.ui.views.calendar

import com.kartjim.todoest.data.entity.Todo

data class CalendarUiState(
    val todos: List<Todo> = emptyList()
)
