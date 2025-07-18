package com.kartjim.todoest.ui.views.calendar

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.kartjim.todoest.ui.component.Layout
import com.kartjim.todoest.ui.router.Routers
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import com.kizitonwose.calendar.core.minusMonths
import com.kizitonwose.calendar.core.now
import com.kizitonwose.calendar.core.plusMonths
import kotlinx.datetime.LocalDate
import kotlinx.datetime.YearMonth
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Composable
fun Calendar(
    navControl: NavHostController,
//    viewModel: HomeViewModel = viewModel { HomeViewModel() },
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

        HorizontalCalendar(
            state = state,
            dayContent = { it ->
                Day(it)
//                Column(
//                    modifier = Modifier
//                        .width(20.dp)
//                        .height(20.dp)
//                        .border(
//                            width = 1.dp,
//                            color = Color.Gray,
////                            shape = TODO()
//                        )
//                ) {
//                    Text("${it.date.day}")
//                }
            }
        )
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
