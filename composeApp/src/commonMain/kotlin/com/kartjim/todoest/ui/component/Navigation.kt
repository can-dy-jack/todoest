package com.kartjim.todoest.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.kartjim.todoest.ui.router.Routers
import com.kartjim.todoest.ui.router.navigateSingleInstance

@Composable
fun Navigation(
    navControl: NavHostController,
    current: Routers
) {

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Routers.entries.forEach{ route ->
            IconButton(
                onClick = {
                    navControl.navigateSingleInstance(route.route)
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