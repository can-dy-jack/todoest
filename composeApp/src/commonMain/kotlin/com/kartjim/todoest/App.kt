package com.kartjim.todoest

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.*
import com.moriafly.salt.ui.SaltDynamicColors
import com.moriafly.salt.ui.SaltTheme
import com.moriafly.salt.ui.saltConfigs
import org.jetbrains.compose.ui.tooling.preview.Preview

import com.kartjim.todoest.head.Title

@Composable
@Preview
fun App() {
//    MaterialTheme {
//        var showContent by remember { mutableStateOf(false) }
//        Column(
//            modifier = Modifier
//                .safeContentPadding()
//                .fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally,
//        ) {
//            Button(onClick = { showContent = !showContent }) {
//                Text("Click me!")
//            }
//            AnimatedVisibility(showContent) {
//                val greeting = remember { Greeting().greet() }
//                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
//                    Image(painterResource(Res.drawable.compose_multiplatform), null)
//                    Text("Compose: $greeting")
//                }
//            }
//        }
//    }

    val isDarkTheme = isSystemInDarkTheme()
    SaltTheme(
        configs = saltConfigs(
            isDarkTheme = isDarkTheme
        ),
        dynamicColors = SaltDynamicColors.default()
    ) {
//        SheetContent()
        // Screen()
//        Home()
//        AppContent()

        Title()
    }
}