package com.kartjim.todoest.ui.views.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kartjim.todoest.data.entity.Todo
import com.kartjim.todoest.ui.component.Layout
import com.kartjim.todoest.ui.component.todo.TodoItem
import com.kartjim.todoest.ui.router.Routers
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun getCurrentDate(): LocalDate =
    Clock.System
        .now()
        .toLocalDateTime(TimeZone.currentSystemDefault())
        .date

@Composable
fun Home(
    viewModel: HomeViewModel = viewModel { HomeViewModel() },
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }
    val now by remember { mutableStateOf(getCurrentDate()) }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val showCompleted = viewModel.showCompleted.collectAsState()
    val completedTodoLen = viewModel.completedTodoLen.collectAsState()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Aside()
            }
        },
        modifier = Modifier.fillMaxSize()
    ) {

        Layout(
            current = Routers.HOME,
        ) {
            Column(
                modifier = modifier
            ) {
                Row(
                    modifier =
                        Modifier
                            .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    drawerState.apply {
                                        if (isClosed) open() else close()
                                    }
                                }
                            }
                        ) {
                            Icon(
                                Icons.Filled.Menu,
                                contentDescription = "aside",
                                modifier = Modifier.padding(5.dp)
                            )
                        }

                        Column(
                            modifier =
                                Modifier
                                    .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {

                            Text(
                                "今日代办"
                            )
                            Text(
                                now.toString(),
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        }

                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            Row {
                                IconButton(onClick = { expanded = !expanded }) {
                                    Icon(
                                        Icons.Default.MoreVert,
                                        contentDescription = "More options"
                                    )
                                }
                                DropdownMenu(
                                    expanded = expanded,
                                    onDismissRequest = { expanded = false },
                                ) {
                                    DropdownMenuItem(
                                        text = {
                                            Text(
                                            if (!showCompleted.value) "显示已完成的任务"
                                            else "隐藏已完成的任务"
                                        )},
                                        onClick = {
                                            viewModel.changeShowCompleted(!showCompleted.value)
                                        },
                                    )
                                }
                            }
                        }
                    }
                }

                Box(
                    modifier = Modifier.fillMaxWidth().weight(1f)
                ) {
                    Column(
                        modifier =
                            Modifier
                                .fillMaxSize()
//                        .padding(10.dp),
                    ) {
                        var showEditDialog by remember { mutableStateOf(false) }
                        var currentTodo by remember { mutableStateOf<Todo?>(null) }
                        val todos by viewModel.todos.collectAsState()
                        LazyColumn {
                            items(
                                todos,
                                key = { it.id }
                            ) { todo ->
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .animateItem()
                                ) {
                                    TodoItem(todo, onItemClick={it ->
                                        currentTodo = it
                                        showEditDialog = true
                                    })
                                }
                            }

                            if (!showCompleted.value) {
                                item{
                                    Row(
                                        modifier = modifier.padding(horizontal = 32.dp, vertical = 10.dp).fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center,
                                    ) {
                                        HorizontalDivider(
                                            modifier = Modifier.weight(1f),
                                        )

                                        Text(
                                            text = "已隐藏 ${completedTodoLen.value} 个完成的任务",
                                            modifier = Modifier.padding(horizontal = 8.dp),
                                            textAlign = TextAlign.Center,
                                            fontSize = 12.sp,
                                            color = Color.Gray
                                        )

                                        HorizontalDivider(
                                            modifier = Modifier.weight(1f),
                                        )
                                    }
                                }
                            }
                        }
                        EditModel(
                            showEditDialog,
                            currentTodo,
                            onDismiss = { showEditDialog = false; },
                        )
                    }

                    Box(
                        modifier = Modifier.fillMaxWidth()
                            .align(Alignment.BottomEnd)
                    ) {
                        AddModel(
                            viewModel,
                            modifier = Modifier.align(Alignment.BottomEnd).padding(15.dp)
                        )
                    }
                }
            }

        }


    }
}