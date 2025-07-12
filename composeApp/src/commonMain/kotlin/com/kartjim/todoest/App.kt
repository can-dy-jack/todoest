package com.kartjim.todoest

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import com.kartjim.todoest.ui.navigation.AppNavigation
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun AppContent() {
    val isDarkTheme = isSystemInDarkTheme()

    MaterialTheme(
    ) {
        AppNavigation()
//        SaltTheme {
//            MainScreen()
//        }
    }
}
