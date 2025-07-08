package com.kartjim.todoest.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dataset
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kartjim.todoest.ui.screen.home.HomePage

/**
 * TODO 路由组件
 */
@Composable
fun Test(label: String) {
    Text("Test Page, $label")
}

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

/**
 * NavHost
 */
@Composable
fun AppNavHost (
    navControl: NavHostController,
    route: Routers,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navControl,
        startDestination = route.route,
        modifier = modifier,
    ) {
        Routers.entries.forEach { item ->
            composable(item.route) {
                when (item) {
                    Routers.HOME -> HomePage()
                    Routers.Calendar -> Test("日历")
                    Routers.FourD -> Test("四象限")
                    Routers.Setting -> Test("设置")
                }
            }
        }
    }
}

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier
) {
    val navControl = rememberNavController()
    val indexPage = Routers.HOME
    var current by rememberSaveable { mutableIntStateOf(indexPage.ordinal) }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationBar(
                windowInsets = NavigationBarDefaults.windowInsets
            ) {
                Routers.entries.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = current == index,
                        onClick = {
                            navControl.navigate(route = item.route)
                            current = index
                        },
                        icon = {
                            Icon(
                                item.icon,
                                contentDescription = item.description
                            )
                        },
                        label = { Text(item.label) }
                    )
                }
            }
        }
    ) { contentPadding ->
        AppNavHost(navControl, indexPage, modifier = Modifier.padding(contentPadding))
    }
}
