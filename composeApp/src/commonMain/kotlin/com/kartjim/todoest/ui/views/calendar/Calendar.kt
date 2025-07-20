package com.kartjim.todoest.ui.views.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kartjim.todoest.ui.component.Layout
import com.kartjim.todoest.ui.router.Routers
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import com.kizitonwose.calendar.core.minusMonths
import com.kizitonwose.calendar.core.now
import com.kizitonwose.calendar.core.plusMonths
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.YearMonth
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Composable
fun Calendar(
    viewModel: CalendarViewModel = viewModel { CalendarViewModel() },
    modifier: Modifier = Modifier,
) {
    Layout(
        current = Routers.Calendar,
    ) {
        val currentMonth = remember { YearMonth.now() }
        val startMonth = remember { currentMonth.minusMonths(100) } // Adjust as needed
        val endMonth = remember { currentMonth.plusMonths(100) } // Adjust as needed
        val firstDayOfWeek = remember { firstDayOfWeekFromLocale() } // Available from the library

        val state = rememberCalendarState(
            startMonth = startMonth,
            endMonth = endMonth,
            firstVisibleMonth = currentMonth,
            firstDayOfWeek = firstDayOfWeek
        )

        val now = dateToTimestamp();
        val todos by viewModel.getTodos(now).collectAsState()

        Column {
            HorizontalCalendar(
                state = state,
                dayContent = { it ->
                    Day(it)
                }
            )
            LazyColumn(
                modifier = Modifier
                    .padding(10.dp)
                    .background(Color.LightGray)
                    .padding(10.dp)
                    .fillMaxSize()
            ) {
                items(todos, key = { it.id }) { todo ->
                    Row {
                        Text(todo.title)
                    }
                }
            }
        }
    }
}

@Composable
fun Day(day: CalendarDay) {
    Box(
        modifier = Modifier
            .aspectRatio(1f), // This is important for square sizing!
        contentAlignment = Alignment.Center
    ) {
        Text(text = day.date.day.toString())
    }
}

@OptIn(ExperimentalTime::class)
fun dateToTimestamp(): Long {
    val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

    val dateTime = LocalDateTime(now.year, now.month, now.day, 8, 0)
    return dateTime.toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()
}
