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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kartjim.todoest.ui.component.Layout
import com.kartjim.todoest.ui.router.Routers
import com.kartjim.todoest.ui.views.calendar.month.MonthView
import com.kartjim.todoest.ui.views.calendar.month.WeekView
import com.kizitonwose.calendar.core.now
import kotlinx.datetime.LocalDate
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

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                MonthView(
                    selectedDate.value,
                    onSelected = {
                        it -> viewModel.changeSelectDate(it)
                    },
                    header = {
                        ToolBar(viewType.value, onChange = {
                            it -> viewModel.changeViewType(it)
                        })
                    }
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
