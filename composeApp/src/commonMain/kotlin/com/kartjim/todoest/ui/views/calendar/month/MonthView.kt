package com.kartjim.todoest.ui.views.calendar.month

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kizitonwose.calendar.compose.CalendarState
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.core.minusMonths
import com.kizitonwose.calendar.core.now
import com.kizitonwose.calendar.core.plusMonths
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.YearMonth
import kotlinx.datetime.isoDayNumber
import kotlinx.datetime.number
import kotlinx.datetime.yearMonth
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Composable
fun MonthView(
    selectedDate: LocalDate? = LocalDate.now(),
    onSelected: (date: LocalDate) -> Unit = {},
    header: @Composable (() -> Unit) = {},
    modifier: Modifier = Modifier,
) {
//    var selectedDate by remember { mutableStateOf<LocalDate?>(LocalDate.now()) }
    var currentMonth by remember { mutableStateOf(YearMonth.now())  }

    val month = remember { YearMonth.now() }
    val startMonth = remember { month.minusMonths(100) }
    val endMonth = remember { month.plusMonths(100) }
    val daysOfWeek = remember { daysOfWeek() }

    val state = rememberCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstVisibleMonth = month,
        firstDayOfWeek = daysOfWeek[1],
    )

    LaunchedEffect(state.firstVisibleMonth.yearMonth) {
        currentMonth = state.firstVisibleMonth.yearMonth;
    }
    val scope = rememberCoroutineScope()

    // 监听外部日期变化 - 切换月份
    LaunchedEffect(selectedDate) {
        if(selectedDate != null) go2Month(scope, state, selectedDate.yearMonth)
    }

    Column(
        modifier= modifier.padding(horizontal = 10.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text("${currentMonth.year}年${currentMonth.month.number}月", fontSize = 22.sp)
            Spacer(modifier = Modifier.weight(1f))
            header()
        }
        DaysOfWeekTitle(daysOfWeek = daysOfWeek)
        HorizontalCalendar(
            state = state,
            dayContent = { Day(
                it,
                selectedDate == it.date,
                onClicked = {day ->
                    onSelected(day.date)
                },
                onOutClicked = { it ->
                    go2Month(scope, state, it.date.yearMonth)
                    onSelected(it.date)
                }
            )},
        )
    }
}

fun go2Month(scope: CoroutineScope, state: CalendarState, month: YearMonth) {
    scope.launch {
        state.animateScrollToMonth(month)
    }
}

@Composable
fun DaysOfWeekTitle(daysOfWeek: List<DayOfWeek>) {
    Row(modifier = Modifier.fillMaxWidth().padding(bottom = 5.dp)) {
        for (dayOfWeek in daysOfWeek) {
            Text(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                text = when(dayOfWeek.isoDayNumber) {
                    7 -> "周一"
                    1 -> "周二"
                    2 -> "周三"
                    3 -> "周四"
                    4 -> "周五"
                    5 -> "周六"
                    6 -> "周日"
                    else -> "xx"
                },
            )
        }
    }
}

@OptIn(ExperimentalTime::class)
@Composable
fun Day(
    day: CalendarDay,
    isSelected: Boolean,
    onClicked: (CalendarDay) -> Unit,
    onOutClicked: (CalendarDay) -> Unit,
) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .clip(shape = CircleShape)
            .background(color =
                if (isSelected)
                    MaterialTheme.colorScheme.primary
                else
                    Color.Transparent
            )
            .clickable(
//                enabled = day.position == DayPosition.MonthDate,
                onClick = {
                    if (day.position == DayPosition.MonthDate) {
                        onClicked(day)
                    } else if (day.position == DayPosition.OutDate || day.position == DayPosition.InDate) {
                        onOutClicked(day)
                    }
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        var color: Color = Color.Unspecified;

        if (day.position == DayPosition.MonthDate) {
            if (isSelected) {
                color = Color.White
            } else if (day.date == LocalDate.now()) {
                color = MaterialTheme.colorScheme.primary
            }
        } else {
            color = Color.Gray;
        }

        Text(
            text = day.date.day.toString(),
            color = color,
            fontWeight = if (day.date == LocalDate.now()) FontWeight.Bold else FontWeight.Normal
        )
    }
}
