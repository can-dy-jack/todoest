package com.kartjim.todoest.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.kartjim.todoest.ui.router.Routers
import com.moriafly.salt.ui.ext.safeMain

@Composable
fun Layout(
    navControl: NavHostController,
    current: Routers,
    content: @Composable (() -> Unit)
) {
    Box(
        modifier = Modifier
            .windowInsetsPadding(
                WindowInsets.safeMain.only(
                    WindowInsetsSides.Top + WindowInsetsSides.Horizontal
                )
            )
    ) {
        Column(
            modifier  = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            ) {
                content()
            }

            Box(
                modifier = Modifier
                    .background(Color(242, 238, 243))
                    .windowInsetsPadding(
                        WindowInsets.safeMain.only(
                            WindowInsetsSides.Bottom + WindowInsetsSides.Horizontal
                        )
                    )

            ) {
                Navigation(navControl,current)
            }
        }
    }
}