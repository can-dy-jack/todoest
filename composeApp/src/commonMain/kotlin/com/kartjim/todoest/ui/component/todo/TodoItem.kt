package com.kartjim.todoest.ui.component.todo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kartjim.todoest.data.entity.Todo

@Composable
fun TodoItem(
    todo: Todo,
    modifier: Modifier = Modifier,
    viewModel: TodoViewModel = viewModel { TodoViewModel() },
) {
    val checked = remember { mutableStateOf(todo.completed) }
    fun checkItem() {
        val cur = !checked.value
        checked.value = cur
        viewModel.checkTodo(cur, todo)
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                onClick = { checkItem() }
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            checked = checked.value,
            onCheckedChange = { checkItem() },
        )
        Text(
            text = todo.title
        )
    }
}