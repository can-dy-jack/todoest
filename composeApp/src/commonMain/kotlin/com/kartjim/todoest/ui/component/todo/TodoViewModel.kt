package com.kartjim.todoest.ui.component.todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kartjim.todoest.data.api.TodoAPI
import com.kartjim.todoest.data.entity.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class TodoViewModel: ViewModel() {
    fun checkTodo(isComplete: Boolean, todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            val newTodo = todo.copy()
            newTodo.completed = isComplete;
            TodoAPI.updateTodo(newTodo);
        }
    }
}