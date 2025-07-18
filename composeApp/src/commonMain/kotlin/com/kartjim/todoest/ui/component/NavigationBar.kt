package com.kartjim.todoest.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.kartjim.todoest.ui.router.LocalNavController
import com.kartjim.todoest.ui.router.Routers
import com.kartjim.todoest.ui.router.navigateSingleInstance

@Composable
fun NavigationBar(
    current: Routers
) {
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
//                    .background(
//                        if(current.route == route.route)
//                            Color(12, 12, 200)
//                        else
//                            Color(242, 238, 243)
//                    )
            ) {
                Icon(
                    route.icon,
                    contentDescription = route.description,
                    tint = if (current.route == route.route)
                        Color.Blue
                    else
                        Color.DarkGray
                )
            }
        }
    }
}