package com.kartjim.todoest.ui.views.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.kartjim.todoest.ui.component.Layout
import com.kartjim.todoest.ui.router.Routers
import kotlinx.coroutines.delay
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
//                            Text(
//                                now.toString(),
//                                fontSize = 12.sp,
//                                color = Color.Gray
//                            )
                        }

                        Box (
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            Row {
                                IconButton(onClick = { expanded = !expanded }) {
                                    Icon(Icons.Default.MoreVert, contentDescription = "More options")
                                }
                                DropdownMenu(
                                    expanded = expanded,
                                    onDismissRequest = { expanded = false },
                                ) {
                                    DropdownMenuItem(
                                        text = { androidx.compose.material3.Text("Option 1") },
                                        onClick = { /* Do something... */ },
                                    )
                                    DropdownMenuItem(
                                        text = { androidx.compose.material3.Text("Option 2") },
                                        onClick = { /* Do something... */ },
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

                        val todos by viewModel.todos.collectAsState()
                        LazyColumn() {
                            items(
                                todos,
                                key = { it.id }
                            ) { todo ->
                                val checked = remember { mutableStateOf(todo.completed)}
                                fun checkItem() {
                                    val cur = !checked.value
                                    checked.value = cur
                                    viewModel.checkTodo(cur, todo)
                                }

                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .animateItem()
                                ) {
                                    Row(
                                        modifier = Modifier
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
                                            text = todo.title + todo.description + todo.startTime
                                        )
                                    }
                                }
                            }
                        }

                    }

                    Box(
                        modifier = Modifier.fillMaxWidth()
                            .align(Alignment.BottomEnd)
                    ) {
                        AddModel(
                            modifier = Modifier.align(Alignment.BottomEnd).padding(15.dp)
                        )
                    }
                }
            }

        }


    }
}