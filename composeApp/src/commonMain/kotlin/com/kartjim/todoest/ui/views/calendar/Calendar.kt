package com.kartjim.todoest.ui.views.calendar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kartjim.todoest.data.dao.DateCountResult
import com.kartjim.todoest.ui.component.Layout
import com.kartjim.todoest.ui.router.Routers
import com.kartjim.todoest.ui.views.calendar.month.MonthView
import com.kartjim.todoest.ui.views.calendar.month.WeekView
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.now
import kotlinx.datetime.LocalDate
import kotlinx.datetime.YearMonth
import kotlinx.datetime.number
import kotlin.time.ExperimentalTime


@OptIn(ExperimentalTime::class)
@Composable
fun Calendar(
    modifier: Modifier = Modifier,
    viewModel: CalendarViewModel = viewModel { CalendarViewModel() },
) {
    Layout(
        current = Routers.Calendar,
    ) {
        val selectedDate = viewModel.selectedDate.collectAsState();
        val todosByDay = viewModel.todosByDay.collectAsState();
        val viewType = viewModel.currentViewType.collectAsState();
        val todoSizeByMonth = viewModel.todoSizeByMonth.collectAsState();
        val currentMonth = viewModel.currentMonth.collectAsState();

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                MonthView(
                    selectedDate.value,
                    currentMonth = currentMonth.value,
                    onCurrentMonthChange = {
                        it -> viewModel.changeCurrentMonth(it)
                    },
                    onSelected = {
                        it -> viewModel.changeSelectDate(it)
                    },
                    header = {
                        ToolBar(viewType.value, onChange = {
                            it -> viewModel.changeViewType(it)
                        })
                    },
                    todoSizeByMonth = todoSizeByMonth.value,
                )

                // 切换动画
//                WeekView()

                DayTodos(todosByDay.value)
            }

            Box(
                Modifier.fillMaxWidth().align(Alignment.BottomEnd)
            ) {
                FloatingActionButton(
                    onClick = {
                        viewModel.changeSelectDate(LocalDate.now())
                    },
                    Modifier.align(Alignment.BottomEnd)
                        .padding(16.dp)
                ) {
                    Text("今", fontSize = 20.sp)
                }
            }
        }
    }
}

@OptIn(ExperimentalTime::class)
@Composable
fun DayRender(day: CalendarDay, color: Color, number: Int) {
    Text(
        text = day.date.day.toString() + "-$number",
        color = color,
        fontWeight = if (day.date == LocalDate.now()) FontWeight.Bold else FontWeight.Normal
    )
}
