package com.kartjim.todoest.ui.views.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kartjim.todoest.data.api.TodoAPI
import com.kartjim.todoest.data.entity.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlin.time.ExperimentalTime

class CalendarViewModel: ViewModel() {
    fun getTodos(date: Long): StateFlow<List<Todo>> {
        return TodoAPI.getTodosByDate(date)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )
    }

    @OptIn(ExperimentalTime::class)
    fun getTodoSize(year: Int, month: Month, day: Int): StateFlow<Int> {
            val startDate = LocalDateTime(year, month, day, 0, 0);
            val start = startDate.toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()
            val end = start + (24 * 60 * 60 * 1000);
            return TodoAPI.getTodoSize(start, end).stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = 0
            )

    }
}

