package com.kartjim.todoest.ui.views.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kartjim.todoest.data.Priority
import com.kartjim.todoest.data.api.TodoAPI
import com.kartjim.todoest.data.entity.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

// TODO
class HomeViewModel : ViewModel() {
    val todos = TodoAPI.getTodos()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addTodo(
        // TODO
        title: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            TodoAPI.addTodo(
                title,
                "",
                0,
                0,
                false,
                Priority.NOT_EMERGENCY_NOT_IMPORTANT,
                -1,
                -1
            )
        }
    }

    fun deleteTodo (todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            delay(500)
            TodoAPI.deleteItem(todo)
        }
    }
}