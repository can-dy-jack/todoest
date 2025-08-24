package com.kartjim.todoest.ui.views.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kartjim.todoest.ui.component.Empty
import com.kartjim.todoest.ui.component.Layout
import com.kartjim.todoest.ui.component.todo.TodoItem
import com.kartjim.todoest.ui.router.Routers
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.CalendarMonth
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import com.kizitonwose.calendar.core.minusMonths
import com.kizitonwose.calendar.core.now
import com.kizitonwose.calendar.core.plusMonths
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.YearMonth
import kotlinx.datetime.number
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
@Composable
fun Calendar(
    viewModel: CalendarViewModel = viewModel { CalendarViewModel() },
    modifier: Modifier = Modifier,
) {
    Layout(
        current = Routers.Calendar,
    ) {
        val daysOfWeek = daysOfWeek(firstDayOfWeek = DayOfWeek.MONDAY)
        val currentMonth = remember { YearMonth.now() }
        val startMonth = remember { currentMonth.minusMonths(100) } // Adjust as needed
        val endMonth = remember { currentMonth.plusMonths(100) } // Adjust as needed
        val firstDayOfWeek = remember { firstDayOfWeekFromLocale() } // Available from the library

        val state = rememberCalendarState(
            startMonth = startMonth,
            endMonth = endMonth,
            firstVisibleMonth = currentMonth,
            firstDayOfWeek = daysOfWeek.first()
        )

        var currentVisibleMonth by remember { mutableStateOf<CalendarMonth?>(null) }
        LaunchedEffect(state) {
            snapshotFlow {
                // 获取当前第一个可见的月份
                state.layoutInfo.visibleMonthsInfo.firstOrNull()?.month
            }.distinctUntilChanged().collect { month ->
                month?.let {
                    currentVisibleMonth = it
                }
            }
        }

        val date = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        val now = dateToTimestamp(date.year, date.month, date.day);

        val current = remember { mutableStateOf(now) }
        val todos by viewModel.getTodos(current.value).collectAsState()

        Column {
            Row(
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp)
            ) {

                Text(
                    "${currentVisibleMonth?.yearMonth?.year} 年 ${currentVisibleMonth?.yearMonth?.month?.number} 月",
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    Icons.Default.MoreVert,
                    contentDescription = ""
                )
            }
            DaysOfWeekTitle(daysOfWeek = daysOfWeek)
            HorizontalCalendar(
                state = state,
                dayContent = { it ->
                    Day(it, current.value, viewModel, changeCurrent = {
                        current.value = it;
                    })
                },
            )

            if (todos.isEmpty()) {
                Empty()
            } else {
                // TODO 抽离出来 - 防止这里数据变化渲染导致整个页面渲染
                LazyColumn(
                    modifier = Modifier
                        .padding(10.dp)
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                        .fillMaxWidth()
                ) {
                    items(todos, key = { it.id }) { todo ->
                        TodoItem(todo)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun Day(day: CalendarDay, current: Long, viewModel: CalendarViewModel, changeCurrent: (date: Long) -> Unit) {
    val size = viewModel.getTodoSize(day.date.year, day.date.month, day.date.day).collectAsState()

    val dayTime = dateToTimestamp(day.date.year, day.date.month, day.date.day);

    Box(
        modifier = Modifier
            .aspectRatio(1f), // This is important for square sizing!
        contentAlignment = Alignment.Center
    ) {
        if (current == dayTime) {

            Box(
                modifier = Modifier.fillMaxSize().padding(5.dp)
                    .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.align(
                        alignment = Alignment.Center
                    )
                ) {
                    Text(
                        text = day.date.day.toString(),
                        color = Color.White,
                    )
                    if (size.value > 0) {
                        Row(
                            modifier = Modifier.height(2.dp).width(18.dp)
                                .background(color = Color.White)
                        ) {}
                    }
                }
            }

        } else {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                TextButton(
                    onClick = {
                        changeCurrent(dayTime)
                    },
                    modifier = Modifier.fillMaxHeight().aspectRatio(1f)
                        .align(alignment = Alignment.Center),
                ) {
                    Column() {
                        Row(
                            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                        ) {
                            Text(
                                text = "${day.date.day}",
                                color = if (day.position == DayPosition.MonthDate)
                                    MaterialTheme.colorScheme.inverseSurface
                                else
                                    Color.Gray,
                                fontSize = 16.sp
                            )
                        }
                        if (size.value > 0) {
                            Row(
                                modifier = Modifier.height(2.dp).width(18.dp)
                                    .background(color = MaterialTheme.colorScheme.primary)
                            ) {}
                        }
                    }

                }
            }
        }

    }
}

@Composable
fun DaysOfWeekTitle(daysOfWeek: List<DayOfWeek>) {
    Row(modifier = Modifier.fillMaxWidth()) {
        for (dayOfWeek in daysOfWeek) {
            Text(
                modifier = Modifier.weight(1f),
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center,
//                text = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
                text = when (dayOfWeek.name) {
                    "MONDAY" -> {
                        "周一"
                    }

                    "TUESDAY" -> {
                        "周二"
                    }

                    "WEDNESDAY" -> {
                        "周三"
                    }

                    "THURSDAY" -> {
                        "周四"
                    }

                    "FRIDAY" -> {
                        "周五"
                    }

                    "SATURDAY" -> {
                        "周六"
                    }

                    "SUNDAY" -> {
                        "周日"
                    }

                    else -> {
                        ""
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalTime::class)
fun dateToTimestamp(year: Int, month: Month, day: Int): Long {
    val dateTime = LocalDateTime(
        year,
        month,
        day,
        8, 0
    )
    return dateTime.toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()
}

@OptIn(ExperimentalTime::class)
fun timestampMonth(timestamp: Long): Int {
    val instant = Instant.fromEpochMilliseconds(timestamp)
    val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
    return localDateTime.month.number;
}
