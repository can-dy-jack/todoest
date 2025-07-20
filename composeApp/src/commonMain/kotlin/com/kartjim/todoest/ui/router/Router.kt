package com.kartjim.todoest.ui.router

import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dataset
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kartjim.todoest.ui.views.matrix.MatrixPage
import com.kartjim.todoest.ui.views.settings.SettingsPage
import com.kartjim.todoest.ui.views.calendar.Calendar
import com.kartjim.todoest.ui.views.home.Home

/**
 * 路由列表
 */
enum class Routers (
    val route: String,
    val label: String,
    val icon: ImageVector,
    val description: String,
) {
    HOME("home", "首页", Icons.Rounded.Home, "首页"),
    Calendar("calendar", "日历", Icons.Rounded.DateRange, "日历"),
    FourD("fourD", "四象限", Icons.Filled.Dataset, "四象限"),
    Setting("setting", "我的", Icons.Rounded.Settings, "我的"),
}

@Composable
fun AppNavHost(
    navControl: NavHostController
) {
    val animationSpec = spring<IntOffset>(
        stiffness = 500f
    )

    NavHost(
        navControl,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface),
        startDestination = "home",
//        enterTransition = {
//            slideIn(
//                animationSpec = animationSpec,
//                initialOffset = {
//                    IntOffset(x = 0, y = 150)
//                }
//            )
//        },
//        exitTransition = {
//            fadeOut(
//                animationSpec = spring(stiffness = 500f)
//            )
//        },
//        popEnterTransition = {
//            fadeIn(
//                animationSpec = spring(stiffness = 500f)
//            )
//        },
//        popExitTransition = {
//            slideOut(
//                animationSpec = animationSpec,
//                targetOffset = {
//                    IntOffset(x = 0, y = 150)
//                }
//            ) + fadeOut(
//                animationSpec = spring(stiffness = 500f)
//            )
//        }
    ) {
        Routers.entries.forEach { item ->
            composable(item.route) {
                when (item) {
                    Routers.HOME -> Home()
                    Routers.Calendar -> Calendar()
                    Routers.FourD -> MatrixPage()
                    Routers.Setting -> SettingsPage()
                }
            }
        }
    }
}

fun NavController.navigateSingleInstance(route: String) {
    val currentBackStack = currentBackStack.value
    // index 0 destination=NavGraph
    // index 1 destination=Destination (start)
    if (currentBackStack.size > 2) {
        repeat(currentBackStack.size - 2) {
            popBackStack()
        }
    }

    navigate(route) {
        popUpTo(currentBackStack[1].destination.route!!) {
            inclusive = true
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

val LocalNavController = compositionLocalOf<NavController> {
    error("Can not get NavController")
}

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    CompositionLocalProvider(
        LocalNavController provides navController
    ) {
        AppNavHost(navController)
    }
}
