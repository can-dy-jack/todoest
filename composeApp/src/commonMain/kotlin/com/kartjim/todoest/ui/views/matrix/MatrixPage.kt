package com.kartjim.todoest.ui.views.matrix

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kartjim.todoest.data.entity.Todo
import com.kartjim.todoest.ui.component.Layout
import com.kartjim.todoest.ui.component.todo.TodoItem
import com.kartjim.todoest.ui.router.Routers

@Composable
fun MatrixPage(
    viewModel: MatrixViewModel = viewModel { MatrixViewModel() }
) {
    val isDark = isSystemInDarkTheme()
    val commonStyle = Modifier
        .fillMaxHeight()
        .padding(8.dp)
        .background(
            if (isDark) Color(30, 35, 40)
            else Color(230, 235, 240),
            shape = RoundedCornerShape(8.dp)
        )
        .padding(8.dp)

    Layout(
        current = Routers.FourD
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(5.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth().weight(1f)
            ) {
                Box(modifier = commonStyle.weight(1f)) {
                    val todoNEIs by viewModel.todos2.collectAsState()
                    Area(todoNEIs, "重要但不紧急", Color.Magenta)
                }
                Box(modifier = commonStyle.weight(1f)) {
                    val todoEIs by viewModel.todos2.collectAsState()
                    Area(todoEIs, "重要且紧急", Color.Red)
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth().weight(1f)
            ) {
                Box(modifier = commonStyle.weight(1f)) {
                    val todoNENIs by viewModel.todos1.collectAsState()
                    Area(todoNENIs)
                }

                Box(modifier = commonStyle.weight(1f)) {
                    val todoENIs by viewModel.todos3.collectAsState()
                    Area(todoENIs, "紧急但不重要", Color.Yellow)
                }
            }
        }
    }
}


@Composable()
fun Area(
    todos: List<Todo>,
    title: String = "不重要且不紧急",
    color: Color = Color.Gray,
    modifier: Modifier = Modifier,
) {
    Column() {
        Row {
            Text(
                title,
                color = color,
                fontSize = 12.sp
            )
        }
        Column(
            modifier = modifier
        ) {
            LazyColumn() {
                items(
                    items = todos,
                    key = {
                        it.id
                    },
                ) {
                    TodoItem(it)
                }
            }
        }
    }

}

@Composable
fun TodoItem(todo: Todo) {
    Row(
        modifier = Modifier.padding(5.dp)
    ) {
        Text(todo.title)
    }
}
