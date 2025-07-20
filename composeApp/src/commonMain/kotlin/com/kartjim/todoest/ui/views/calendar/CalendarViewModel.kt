package com.kartjim.todoest.ui.views.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kartjim.todoest.data.api.TodoAPI
import com.kartjim.todoest.data.entity.Todo
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class CalendarViewModel: ViewModel() {
    fun getTodos(date: Long): StateFlow<List<Todo>> {
        return TodoAPI.getTodosByDate(date)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )
    }
}