package com.kartjim.todoest.ui.views.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kartjim.todoest.data.api.TodoAPI
import com.kartjim.todoest.data.entity.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class CalendarViewModel: ViewModel() {
    private val _timestamp = MutableStateFlow(getNowTimestamp())
    val timestamp = _timestamp.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val todos = _timestamp
        .flatMapLatest { timestamp ->
            TodoAPI.getTodosByDate(timestamp)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun updateTimestamp(value: Long) {
        _timestamp.update{ value }
    }

    private fun getNowTimestamp(): Long {
        val date = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        // 当前天 0 点
        val now = dateToTimestamp(date.year, date.month, date.day);
        return now
    }
}

