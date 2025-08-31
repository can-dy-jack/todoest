package com.kartjim.todoest.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kartjim.todoest.ui.router.LocalNavController
import com.kartjim.todoest.ui.router.Routers
import com.kartjim.todoest.ui.router.navigateSingleInstance

@Composable
fun NavigationBar(
    current: Routers
) {
    val isDark = isSystemInDarkTheme();

    Box(
        modifier = Modifier
            .height(1.dp).fillMaxWidth()
            .background(
                if (isDark) Color(50, 50, 50)
                else Color(220, 230, 240)
            )
    ) {}
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        val navController = LocalNavController.current
        Routers.entries.forEach{ route ->
            IconButton(
                onClick = {
                    navController.navigateSingleInstance(route.route)
                    // navController.navigate(route.route)
                },
                modifier = Modifier
                    .weight(1f)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        route.icon,
                        contentDescription = route.description,
                        tint = if (current.route == route.route)
                            MaterialTheme.colorScheme.primary
                        else
                            Color.Gray
                    )
                }
            }
        }
    }
}