package com.kartjim.todoest.ui.views.home

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kartjim.todoest.data.entity.Todo
import com.kartjim.todoest.ui.component.todo.EditOrAddTodoItem
import com.kartjim.todoest.ui.component.todo.EditOrAddTodoItemMode

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditModel(
    showState: Boolean,
    todo: Todo?,
    onDismiss: () -> Unit,
    viewModel: HomeViewModel = viewModel {
        HomeViewModel()
    }
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )

    if (showState && todo != null) {
        EditOrAddTodoItem(
            sheetState,
            onDismiss,
            onConfirm = { title, description, startTime, endTime, priority ->
                viewModel.updateTodo(todo, title, description, startTime, endTime, priority)
            },
            todo,
            mode = EditOrAddTodoItemMode.EDIT
        )
    }
}