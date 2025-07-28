package com.kartjim.todoest.ui.views.matrix

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kartjim.todoest.ui.component.Layout
import com.kartjim.todoest.ui.router.Routers


@Composable
fun MatrixPage() {
    val commonStyle = Modifier
        .fillMaxHeight()
        .padding(8.dp)
        .background(
            MaterialTheme.colorScheme.secondaryContainer,
            shape = RoundedCornerShape(8.dp)
        )
        .padding(8.dp)

    Layout(
        current = Routers.FourD
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(5.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth().weight(1f)
            ) {
                Box(
                    modifier = commonStyle.weight(1f)
                ) {
                    Column {
                        Row {
                            Text(
                                "重要但不紧急",
                                color = Color.Magenta,
                                fontSize = 12.sp
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth()
                                .weight(1f)
                        ) {

                        }
                    }
                }
                Box(
                    modifier = commonStyle.weight(1f)
                ) {
                    Text(
                        "重要且紧急",
                        color = Color.Red,
                        fontSize = 12.sp
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth().weight(1f)
            ) {
                Box(
                    modifier = commonStyle.weight(1f)
                ) {
                    Text(
                        "不重要且不紧急",
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                }
                Box(
                    modifier = commonStyle.weight(1f)
                ) {
                    Text(
                        "紧急但不重要",
                        color = Color.Yellow,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}
