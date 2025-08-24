package com.kartjim.todoest.ui.views.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kartjim.todoest.data.Priority
import kotlinx.datetime.TimeZone
import kotlinx.datetime.number
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddModel(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel { HomeViewModel() },
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
        ModalBottomSheet(
            modifier = Modifier.fillMaxHeight(),
            sheetState = sheetState,
            onDismissRequest = {
                showBottomSheet = false
            }
        ) {
            val title = rememberTextFieldState()
            val description = rememberTextFieldState()
            var priority = remember { mutableStateOf(Priority.NOT_EMERGENCY_NOT_IMPORTANT) }
            val startTime = remember { mutableLongStateOf(0) }
            val endTime = remember { mutableLongStateOf(0) }
            val focusRequester = remember { FocusRequester() }
            LaunchedEffect(Unit) {
                focusRequester.requestFocus()
            }

            Column(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            ) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester),
                    label = {
                        Row {
                            Text(
                                "标题",
                                modifier = Modifier,
                                color = Color(100, 100, 100),
                                fontSize = 14.sp
                            )
                            Text("*", color = Color.Red)
                        }
                    },
                    state = title,
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
//                        focusedIndicatorColor = Color.Green
                    ),
                    contentPadding = PaddingValues(8.dp),
                    lineLimits = TextFieldLineLimits.SingleLine,
                )

                TextField(
                    state = description,
                    modifier = Modifier
                        .fillMaxWidth(),
//                    enabled = title.text.isNotEmpty(),
                    label = {
                        Text(
                            "补充描述：",
                            modifier = Modifier,
                            color = Color(100, 100, 100),
                            fontSize = 14.sp
                        )
                    },
                    lineLimits = TextFieldLineLimits.MultiLine(1, 5),
                    contentPadding = PaddingValues(8.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
//                        focusedIndicatorColor = Color.Green
                    )
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
//                    contentAlignment = Alignment.CenterEnd
                ) {
                    val showStartTimeDialog = remember { mutableIntStateOf(0) }
                    TextButton(
                        onClick = {
                            showStartTimeDialog.value = 1
                        },
//                        modifier = Modifier.hoverable()
                    ) {
                        if (startTime.value == 0L)
                            Text("选择开始日期")
                        else
                            TimestampDisplay(
                                startTime.value,
                                text = { date ->
                                    "开始日期：${date}"
                                }
                            )

                    }
                    TextButton(
                        onClick = {
                            showStartTimeDialog.value = 2
                        },
                        modifier = Modifier.padding(horizontal = 5.dp)
                    ) {
                        if (endTime.value == 0L)
                            Text("选择结束日期")
                        else
                            TimestampDisplay(
                                endTime.value,
                                text = { date ->
                                    "结束日期：${date}"
                                }
                            )

                    }
                    if (showStartTimeDialog.value > 0) {
                        DatePickerModal(
                            onDateSelected = {
                                if (it != null) {
                                    if (showStartTimeDialog.value == 1) {
                                        startTime.value = it
                                    } else { // 2
                                        endTime.value = it
                                    }
                                }
                            },
                            onDismiss = {
                                showStartTimeDialog.value = 0
                            }
                        )
                    }


                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    var expanded by remember { mutableStateOf(false) }
                    Box() {
                        IconButton(
                            onClick = { expanded = !expanded }
                        ) {
                            Icon(
                                Icons.Default.Flag,
                                contentDescription = "选择优先级",
                                tint =
                                    when (priority.value) {
                                        Priority.NOT_EMERGENCY_IMPORTANT -> Color.Magenta
                                        Priority.EMERGENCY_IMPORTANT -> Color.Red
                                        Priority.EMERGENCY_NOT_IMPORTANT -> Color.Yellow
                                        Priority.NOT_EMERGENCY_NOT_IMPORTANT -> Color.Gray
                                    }

                            )
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("不重要不紧急", color = Color.Gray) },
                                onClick = {
                                    priority.value = Priority.NOT_EMERGENCY_NOT_IMPORTANT
                                    expanded = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("重要但不紧急", color = Color.Magenta) },
                                onClick = {
                                    priority.value = Priority.NOT_EMERGENCY_IMPORTANT
                                    expanded = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("紧急但不重要", color = Color.Yellow) },
                                onClick = {
                                    priority.value = Priority.EMERGENCY_NOT_IMPORTANT
                                    expanded = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("重要且紧急", color = Color.Red) },
                                onClick = {
                                    priority.value = Priority.EMERGENCY_IMPORTANT
                                    expanded = false
                                }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    FilledTonalButton(
                        onClick = {
                            if (!title.text.isEmpty()) {
                                viewModel.addTodo(
                                    title = title.text.toString(),
                                    description = description.text.toString(),
                                    startTime = startTime.value,
                                    endTime = endTime.value,
                                    priority = priority.value,
                                )
                                showBottomSheet = false
                            }
                        },
                    ) {
                        Text("新增")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis) // 时区问题
                onDismiss()
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

@OptIn(ExperimentalTime::class)
@Composable
fun TimestampDisplay(timestamp: Long, text: (date: String) -> String) {
    val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault());
    // 假设时间戳是毫秒
    val dateString = remember(timestamp) {
        val instant = Instant.fromEpochMilliseconds(timestamp)
        val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())

        val year = if (now.year == localDateTime.year) "" else "${localDateTime.year}-"

        "${year}${localDateTime.month.number.padStart(2)}-${
            localDateTime.day.padStart(
                2
            )
        } "
//        +
//                "${localDateTime.hour.padStart(2)}:${localDateTime.minute.padStart(2)}:${
//                    localDateTime.second.padStart(
//                        2
//                    )
//                }"
    }

    Text(text = text(dateString))
}

// 辅助函数，用于补零
private fun Int.padStart(length: Int) = toString().padStart(length, '0')

