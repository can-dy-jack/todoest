package com.kartjim.todoest.ui.views.calendar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.kartjim.todoest.ui.component.Layout
import com.kartjim.todoest.ui.router.Routers
import com.kartjim.todoest.ui.screen.home.HomeViewModel
import com.moriafly.salt.ui.Text

@Composable
fun Calendar(
    navControl: NavHostController,
    viewModel: HomeViewModel = viewModel { HomeViewModel() },
    modifier: Modifier = Modifier,
) {
    Layout(
        navControl = navControl,
        current = Routers.Calendar,
    ) {
        Text("calendar")
    }
}