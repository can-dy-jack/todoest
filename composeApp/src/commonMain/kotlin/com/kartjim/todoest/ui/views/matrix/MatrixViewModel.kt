package com.kartjim.todoest.ui.views.matrix

import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kartjim.todoest.data.Priority
import com.kartjim.todoest.data.api.TodoAPI
import com.kartjim.todoest.data.entity.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MatrixViewModel: ViewModel() {
    val todos = TodoAPI.getTodos()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        );

    val todos1 = TodoAPI
        .getTodosByPriority(Priority.NOT_EMERGENCY_NOT_IMPORTANT)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        );
    val todos2 = TodoAPI
        .getTodosByPriority(Priority.NOT_EMERGENCY_IMPORTANT)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        );
    val todos3 = TodoAPI
        .getTodosByPriority(Priority.EMERGENCY_NOT_IMPORTANT)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        );
    val todos4 = TodoAPI
        .getTodosByPriority(Priority.EMERGENCY_IMPORTANT)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}