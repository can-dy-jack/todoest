package com.kartjim.todoest.ui.views.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Help
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Inbox
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun Aside() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        Column(
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .weight(1f)
//            .verticalScroll(rememberScrollState())
        ) {
            GroupItem(Icons.Outlined.CalendarToday, "今日", 1)
            GroupItem(Icons.Outlined.Inbox, "收集箱", 23)

            HorizontalDivider(modifier = Modifier.padding(10.dp))

            GroupItem(Icons.Outlined.Inbox, "自定义分组", 2)
            GroupItem(Icons.Outlined.Inbox, "自定义分组", 2)
            GroupItem(Icons.Outlined.Inbox, "自定义分组", 2)
            GroupItem(Icons.Outlined.Inbox, "自定义分组", 2)
        }

        Row {
            IconButton(
                onClick = {}
            ) {
                Text("xx")
            }

            Spacer(modifier = Modifier.weight(1f))

            IconButton(
                onClick = {}
            ) {
                Text("xx")
            }
        }
    }
}

@Composable
fun GroupItem(
    img: ImageVector,
    label: String,
    number: Number
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(32.dp))
            .clickable(
                onClick = {}
            )
            .padding(16.dp)
    ) {
        Icon(
            img,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(end = 10.dp)
        )
        Text(label)
        Spacer(modifier = Modifier.weight(1f))
        Text("$number")
    }
}
