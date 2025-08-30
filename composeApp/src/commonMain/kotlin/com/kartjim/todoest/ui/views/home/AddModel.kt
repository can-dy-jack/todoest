package com.kartjim.todoest.ui.views.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.kartjim.todoest.ui.component.todo.EditOrAddTodoItem
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalMaterial3Api::class, ExperimentalTime::class)
@Composable
fun AddModel(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier,
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )

    FloatingActionButton(
        onClick = {
            showBottomSheet = true
        },
        modifier = modifier
    ) {
        Icon(Icons.Filled.Add, "新增")
    }

    if (showBottomSheet) {
        EditOrAddTodoItem(
            sheetState,
            {
                showBottomSheet = false
            },
            onConfirm = { title, description, startTime, endTime, priority ->
                viewModel.addTodo(
                    title,
                    description,
                    startTime,
                    endTime,
                    priority,
                )
            }
        )
    }
}
