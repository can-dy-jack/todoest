package com.kartjim.todoest.ui.views.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kartjim.todoest.data.entity.Todo
import com.kartjim.todoest.ui.component.Empty
import com.kartjim.todoest.ui.component.todo.TodoItem
import kotlin.contracts.Effect
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Composable
fun DayTodos(
    todos: List<Todo>,
) {
    if (todos.isEmpty()) {
        Empty()
    } else {
        LazyColumn(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
        ) {
            items(todos, key = { it.id }) { todo ->
                TodoItem(todo)
            }
        }
    }
}