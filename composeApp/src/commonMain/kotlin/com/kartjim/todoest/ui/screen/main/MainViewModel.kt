package com.kartjim.todoest.ui.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kartjim.todoest.data.Priority
import com.kartjim.todoest.data.api.TodoAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val todos = TodoAPI.getTodos()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun insert(title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            TodoAPI.addTodo(title, "", 0, 0, false, Priority.NOT_EMERGENCY_NOT_IMPORTANT, 0, 0)
        }
    }

//    fun deleteItem(todo: Todo) {
//        viewModelScope.launch(Dispatchers.IO) {
//            Todo.deleteItem(todo)
//        }
//    }
}