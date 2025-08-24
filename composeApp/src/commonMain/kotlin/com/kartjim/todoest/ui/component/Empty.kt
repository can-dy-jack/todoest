package com.kartjim.todoest.ui.component

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AssignmentTurnedIn
import androidx.compose.material.icons.filled.HourglassEmpty
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Empty(
    modifier: Modifier = Modifier,
    text: String = "无任务"
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier.weight(1f))
        Icon(
            Icons.Default.AssignmentTurnedIn,
            contentDescription = "",
            modifier = Modifier
                .height(100.dp)
                .width(100.dp)
                .padding(10.dp),
            tint = Color(150, 150, 150)
        )

        Text(
            text,
            fontSize = 18.sp
        )
        Spacer(modifier.weight(1f))
    }
}
