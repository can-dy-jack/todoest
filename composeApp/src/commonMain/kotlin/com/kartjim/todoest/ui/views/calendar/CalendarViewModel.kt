package com.kartjim.todoest.ui.views.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kartjim.todoest.data.api.TodoAPI
import com.kartjim.todoest.data.entity.Todo
import com.kizitonwose.calendar.core.now
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.datetime.LocalDate
import kotlinx.datetime.yearMonth
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class CalendarViewModel: ViewModel() {
    private val _selectedDate = MutableStateFlow(LocalDate.now())
    val selectedDate = _selectedDate.asStateFlow();

    fun changeSelectDate(data: LocalDate) {
        _selectedDate.update { data }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private val _todosByDay = _selectedDate
        .flatMapLatest { date ->
            TodoAPI.getTodosByDate(date.toEpochDays())
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    val todosByDay = _todosByDay
}

