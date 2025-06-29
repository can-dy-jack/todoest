package com.kartjim.todoest

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Navigation(modifier: Modifier = Modifier) {
    NavigationBar () {
        NavigationBarItem (
            onClick = {},
            selected = true,
            icon = {
                Icon(
                    Icons.Rounded.Home,
                    contentDescription = "home"
                )
            },
//            modifier = TODO(),
//            enabled = TODO(),
            label = {
                Text("主页")
            },
//            alwaysShowLabel = TODO(),
//            colors = ,
//            interactionSource = TODO(),
        )

        NavigationBarItem(
            onClick = {},
            selected = false,
            icon = {
                Icon(Icons.Rounded.DateRange, contentDescription = "")
            },
            label = {
                Text("日历")
            }
        )
        NavigationBarItem(
            onClick = {},
            selected = false,
            icon = {
                Icon(
                    Icons.Filled.Star,
                    contentDescription = "",
                )
            },
            label = {
                Text("四象限")
            }
        )
        NavigationBarItem(
            onClick = {},
            selected = false,
            icon = {
                Icon(Icons.Rounded.Settings, contentDescription = "")
            },
            label = {
                Text("我的")
            }
        )
    }
}
