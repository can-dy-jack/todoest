package com.kartjim.todoest.ui.component.todo

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kartjim.todoest.data.Priority
import com.kartjim.todoest.data.entity.Todo
import com.kizitonwose.calendar.core.now
import kotlinx.datetime.LocalDate
import kotlinx.datetime.number
import kotlin.time.ExperimentalTime

enum class EditOrAddTodoItemMode {
    ADD,
    EDIT,
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalTime::class)
@Composable
fun EditOrAddTodoItem(
    sheetState: SheetState,
    onDismiss: () -> Unit,
    onConfirm: (
        title: String,
        description: String,
        startTime: Long,
        endTime: Long,
        priority: Priority,
    ) -> Unit,
    defaultTodo: Todo? = null,
    mode: EditOrAddTodoItemMode = EditOrAddTodoItemMode.ADD,
    modifier: Modifier = Modifier,
) {
    val defaultTitle = defaultTodo?.title
    val defaultDesc = defaultTodo?.description
    val defaultPriority = defaultTodo?.priority ?: 1
    val defaultStart = timeStamp2Date(defaultTodo?.startTime ?: LocalDate.now().toEpochDays())
    val defaultEnd = timeStamp2Date(defaultTodo?.endTime ?: LocalDate.now().toEpochDays())

    ModalBottomSheet(
        modifier = modifier.fillMaxHeight(),
        sheetState = sheetState,
        onDismissRequest = onDismiss
    ) {
        val title = rememberTextFieldState(defaultTitle ?: "")
        val description = rememberTextFieldState(defaultDesc ?: "")
        val priority = remember {
            mutableStateOf(
                Priority.from(defaultPriority) ?: Priority.NOT_EMERGENCY_NOT_IMPORTANT
            )
        }
        val startTime =
            remember { mutableStateOf(defaultStart) }
        val endTime =
            remember { mutableStateOf(defaultEnd) }

        val focusRequester = remember { FocusRequester() }
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }

        fun onOk() {
            if (!title.text.isEmpty()) {
                onConfirm(
                    title.text.toString(),
                    description.text.toString(),
                    date2timeStamp(startTime.value),
                    date2timeStamp(endTime.value),
                    priority.value,
                )
            }
            onDismiss()
        }

        Column {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester),
                    placeholder = {
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
                    lineLimits = TextFieldLineLimits.SingleLine,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    onKeyboardAction = {
                        if (!title.text.isEmpty()) onOk()
                    }
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
            ) {
                var expanded by remember { mutableStateOf(false) }
                Box {
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

                val showStartTimeDialog = remember { mutableIntStateOf(0) }
                TextButton(
                    onClick = {
                        showStartTimeDialog.value = 1
                    },
                ) {
                    Text(startTime.value)
                }
                TextButton(
                    onClick = {
                        showStartTimeDialog.value = 2
                    },
                ) {
                    Text(endTime.value)
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

                Spacer(modifier = Modifier.weight(1f))

                FilledTonalButton(
                    onClick = {
                        onOk()
                    },
                ) {
                    if (mode == EditOrAddTodoItemMode.EDIT) {
                        Text("保存")
                    } else {
                        Text("新增")
                    }
                }
            }

            Box(
                modifier = Modifier
                    .padding(horizontal = 21.dp, vertical = 10.dp)
            ) {
                BasicTextField(
                    state = description,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.dp),
//                        readOnly = title.text.isNotEmpty(),
                    lineLimits = TextFieldLineLimits.MultiLine(1, 10),
                    textStyle = TextStyle(color = if (isSystemInDarkTheme()) Color.White else Color.DarkGray)
                )

                if (description.text.isEmpty()) {
                    Text("备注:", color = Color.Gray, fontSize = 14.sp)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    onDateSelected: (selected: String?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                val millis = datePickerState.selectedDateMillis
                if (millis !== null) {
                    val days = millis / 1000 / 60 / 60 / 24
                    onDateSelected(timeStamp2Date(days))
                } else {
                    onDateSelected(null)
                }
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
        DatePicker(state = datePickerState, showModeToggle = false)
    }
}

fun timeStamp2Date(millis: Long): String {
    val date = LocalDate.fromEpochDays(millis)
    val month = date.month.number
    val day = date.day
    return "${date.year}-${if (month < 10) '0' + month else month}-${if (day < 10) '0' + day else day}"
}

fun date2timeStamp(date: String): Long {
    val dateList = date.split("-")
    val year = dateList[0].toInt()
    val month = dateList[1].toInt()
    val day = dateList[2].toInt()

    return LocalDate(year, month, day).toEpochDays()
}
