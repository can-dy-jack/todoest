package com.kartjim.todoest.ui.screen.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material.icons.filled.ArrowCircleRight
import androidx.compose.material.icons.filled.BorderRight
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Grid3x3
import androidx.compose.material.icons.filled.Mode
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.SettingsApplications
import androidx.compose.material.icons.filled.SettingsCell
import androidx.compose.material.icons.filled.SwitchRight
import androidx.compose.material.icons.filled.TurnRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moriafly.salt.ui.Icon
import com.moriafly.salt.ui.Text
import org.jetbrains.compose.resources.painterResource
import todoest.composeapp.generated.resources.Res
import todoest.composeapp.generated.resources.compose_multiplatform

@Composable
fun SettingsPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Text("设置", fontSize = 20.sp)
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(
                    Color(245,245,245)
                )
        ) {}
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painterResource(Res.drawable.compose_multiplatform),
                    null,
                    modifier = Modifier
                        .height(80.dp)
                        .width(80.dp)
                        .background(
                            shape = RoundedCornerShape(
                                percent = 50
                            ),
                            color = Color(245,245,245)
                        )
                )
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(10.dp),
                ) {
                    Text(
                        "Kart Jim",
                        modifier = Modifier,
                        fontSize = 16.sp
                    )

                    Icon(
                        Icons.Filled.Accessibility,
                        contentDescription = ""
                    )
                }
                Icon(
                    Icons.Filled.ChevronRight,
                    contentDescription = ""
                )
            }

            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .background(
                        Color(240, 245, 245),
                        shape = RoundedCornerShape(5.dp)
                    )
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Filled.Grid3x3,
                    contentDescription = "",
                    tint = Color.Blue
                )
                Text(
                    "选项",
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 10.dp)
                )
                Icon(
                    Icons.Filled.ChevronRight,
                    contentDescription = ""
                )
            }

            val data = listOf(1, 2, 3, 4, 5, 6, 7, 8, 15, 16)
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .background(
                        Color(242, 237, 246),
                        shape = RoundedCornerShape(5.dp)
                    )
            ) {
                LazyColumn() {
                    items(data) { item ->
                        Row(
                            modifier = Modifier
                                .padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Filled.Grid3x3,
                                contentDescription = "",
                                tint = Color.Blue
                            )
                            Text(
                                "选项",
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = 10.dp)
                            )
                            Icon(
                                Icons.Filled.ChevronRight,
                                contentDescription = ""
                            )
                        }
                    }
                }
            }
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .background(
                        Color(242, 237, 246),
                        shape = RoundedCornerShape(5.dp)
                    )
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Filled.Grid3x3,
                    contentDescription = "",
                    tint = Color.Blue
                )
                Text(
                    "选项",
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 10.dp)
                )
                Icon(
                    Icons.Filled.ChevronRight,
                    contentDescription = ""
                )
            }

        }
    }
}