package com.kartjim.todoest

import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import com.kartjim.todoest.ui.router.AppNavigation
import com.kartjim.todoest.ui.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun AppContent() {
    AppTheme {
        Surface {
            AppNavigation()
        }
    }
}
