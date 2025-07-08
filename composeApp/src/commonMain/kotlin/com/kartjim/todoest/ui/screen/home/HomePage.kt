package com.kartjim.todoest.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun getCurrentDate(): LocalDate =
    Clock.System
        .now()
        .toLocalDateTime(TimeZone.currentSystemDefault())
        .date

@Composable
fun HomePage(
    viewModel: HomeViewModel = viewModel { HomeViewModel() },
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }
    val now by remember { mutableStateOf(getCurrentDate()) }

    Column(
        modifier = modifier
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                Icons.Filled.Menu,
                contentDescription = "aside",
                modifier = Modifier.padding(5.dp),
            )
            Column(
                modifier =
                    Modifier
                        .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text("今日代办")
                Text(now.toString(), fontSize = 12.sp, color = Color.Gray)
            }

            Box {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(Icons.Default.MoreVert, contentDescription = "More options")
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                ) {
                    DropdownMenuItem(
                        text = { Text("Option 1") },
                        onClick = { /* Do something... */ },
                    )
                    DropdownMenuItem(
                        text = { Text("Option 2") },
                        onClick = { /* Do something... */ },
                    )
                }
            }
        }
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(10.dp),
        ) {

            val todos by viewModel.todos.collectAsState()
            LazyColumn() {
                items(todos) { todo ->
                    Column(
                        modifier = Modifier.clickable(
                            onClick = {

                            }
                        ).fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Checkbox(
                                checked = false,
                                onCheckedChange = {
                                    viewModel.deleteTodo(todo)
                                }
                            )
                            Text(
                                text = "${todo.title}"
                            )
                        }
                    }
                }
            }

        }

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            FloatingActionButton(
                onClick = {
//                    TODO
                    viewModel.addTodo()
                },
                modifier = Modifier.align(Alignment.BottomEnd).padding(15.dp),
            ) {
                Icon(Icons.Filled.Add, "新增")
            }
        }
    }
}
