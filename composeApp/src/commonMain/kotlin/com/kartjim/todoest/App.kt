package com.kartjim.todoest

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import kotlinx.datetime.LocalDate
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun getCurrentDate(): LocalDate {
    return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
}

@Composable
@Preview
fun App() {
    val isDarkTheme = isSystemInDarkTheme()
    var expanded by remember { mutableStateOf(false) }
    val now by remember { mutableStateOf(getCurrentDate()) }

    val data = remember {
        mutableListOf(
            Todos(1, "UI设计", "", "2025-06-25"),
            Todos(2, "UX设计", "", "2025-06-25"),
            Todos(2, "UX设计", "", "2025-06-25"),
            Todos(2, "UX设计", "", "2025-06-25"),
            Todos(2, "UX设计", "", "2025-06-25"),
            Todos(2, "UX设计", "", "2025-06-25"),
            Todos(2, "UX设计", "", "2025-06-25"),
            Todos(2, "UX设计", "", "2025-06-25"),
            Todos(2, "UX设计", "", "2025-06-25"),
            Todos(2, "UX设计", "", "2025-06-25"),
            Todos(2, "UX设计", "", "2025-06-25"),
            Todos(2, "UX设计", "", "2025-06-25"),
            Todos(2, "UX设计", "", "2025-06-25"),
            Todos(2, "UX设计", "", "2025-06-25"),
            Todos(2, "UX设计", "", "2025-06-25"),
            Todos(2, "UX设计4555", "", "2025-06-25"),
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
                    modifier = Modifier.padding(5.dp)
                )
                Column(
                    modifier = Modifier
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("今日代办")
                    Text(now.toString(), fontSize = 12.sp, color = Color.Gray)
                }

                Box{
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "More options")
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Option 1") },
                            onClick = { /* Do something... */ }
                        )
                        DropdownMenuItem(
                            text = { Text("Option 2") },
                            onClick = { /* Do something... */ }
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(
                        horizontal = 10.dp,
                        vertical = 0.dp
                    )
            ) {
                for (item in data) {
                    Text("title: ${item.title}", modifier = Modifier.height(70.dp))
                }
            }
            Row {
                Navigation()
            }
        }
    }

}
