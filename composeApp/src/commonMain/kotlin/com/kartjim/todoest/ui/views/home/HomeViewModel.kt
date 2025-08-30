package com.kartjim.todoest.ui.views.home

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kartjim.todoest.data.Priority
import com.kartjim.todoest.data.api.TodoAPI
import com.kartjim.todoest.data.entity.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _todos = MutableStateFlow<List<Todo>>(emptyList())
    private val _completedTodoLen = MutableStateFlow(0)

    val todos = _todos.asStateFlow()
    val completedTodoLen = _completedTodoLen.asStateFlow()

    init {
        loadTodos()
    }

    private fun loadTodos() {
        viewModelScope.launch(Dispatchers.IO) {
            TodoAPI.getTodos().collectLatest { todoList ->
                _todos.value = todoList.filter { item -> if (_showCompleted.value) true else !item.completed }
                _completedTodoLen.value = todoList.filter { item -> item.completed }.size
            }
        }
    }

    fun addTodo(
        title: String,
        description: String,
        startTime: Long,
        endTime: Long,
        priority: Priority = Priority.NOT_EMERGENCY_NOT_IMPORTANT
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            TodoAPI.addTodo(
                title,
                description,
                startTime,
                endTime,
                false,
                priority,
                -1,
                -1
            )
        }
    }

    fun updateTodo(
        todo: Todo,
        title: String,
        description: String,
        startTime: Long,
        endTime: Long,
        priority: Priority = Priority.NOT_EMERGENCY_NOT_IMPORTANT
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val newTodo = todo.copy()
            newTodo.title = title
            newTodo.description = description
            newTodo.startTime = startTime
            newTodo.endTime = endTime
            newTodo.priority = priority.type
            TodoAPI.updateTodo(newTodo)
        }
    }

    fun checkTodo(isComplete: Boolean, todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            todo.completed = isComplete;
            TodoAPI.updateTodo(todo);
        }
    }

    fun deleteTodo (todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            delay(500)
            TodoAPI.deleteItem(todo)
        }
    }

    private var _showCompleted = MutableStateFlow(false)
    val showCompleted = _showCompleted.asStateFlow()
    fun changeShowCompleted(show: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            _showCompleted.value = show
            loadTodos()
        }
    }
}