package com.kartjim.todoest

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moriafly.salt.ui.ext.safeMainPadding
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val isDarkTheme = isSystemInDarkTheme()

    val data = remember {
        mutableListOf(
            Todos(1, "UI设计", "", "2025-06-25"),
            Todos(2, "UX设计", "", "2025-06-25")
        )
    }

    MaterialTheme {
        Column(
            modifier = Modifier
                .safeMainPadding()
//                .safeContentPadding()
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon (
                    Icons.Filled.Menu,
                    contentDescription = "aside",
                    modifier = Modifier
//                        .width(24.dp)
//                        .padding(3.dp)
                )
                Column(
                    modifier = Modifier
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("今日代办")
                    Text("2025/06/25", fontSize = 12.sp, color = Color.Gray)
                }
                Spacer(modifier = Modifier.size(30.dp))
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .background(Color.Blue)
            ) {
                for (item in data) {
                    Text("title: ${item.title}")
                }
            }
            Row {
                Text("foot")
            }
        }
    }

}
