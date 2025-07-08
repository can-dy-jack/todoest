package com.kartjim.todoest

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.kartjim.todoest.ui.navigation.AppNavigation
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun AppContent() {
    val isDarkTheme = isSystemInDarkTheme()

    MaterialTheme(
    ) {
//        Column(
//            modifier =
                Modifier
//                    .fillMaxSize()
//                    .windowInsetsPadding(
//                        WindowInsets.safeDrawing.only(WindowInsetsSides.Top),
//                    ),
//        ) {
            AppNavigation()
//        }
//        SaltTheme {
//            MainScreen()
//        }
    }
}
