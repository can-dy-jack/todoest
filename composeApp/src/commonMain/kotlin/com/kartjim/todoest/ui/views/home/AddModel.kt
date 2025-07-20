package com.kartjim.todoest.ui.views.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import kotlinx.datetime.TimeZone
import kotlinx.datetime.number
import kotlinx.datetime.toLocalDateTime
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
                        unfocusedIndicatorColor = Color.Gray,
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
                        .padding(5.dp)
                        .fillMaxWidth(),
//                    contentAlignment = Alignment.CenterEnd
                ) {
                    val showStartTimeDialog = remember { mutableIntStateOf(0) }
                    OutlinedButton(
                        onClick = {
                            showStartTimeDialog.value = 1
                        },
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 5.dp),
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
                    OutlinedButton(
                        onClick = {
                            showStartTimeDialog.value = 2
                        },
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 5.dp),
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
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Button(
                        onClick = {
                            if (!title.text.isEmpty()) {
                                viewModel.addTodo(
                                    title = title.text.toString(),
                                    description = description.text.toString(),
                                    startTime = startTime.value,
                                    endTime = endTime.value,
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
    // 假设时间戳是毫秒
    val dateString = remember(timestamp) {
        val instant = Instant.fromEpochMilliseconds(timestamp)
        val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
        "${localDateTime.year}-${localDateTime.month.number.padStart(2)}-${
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

