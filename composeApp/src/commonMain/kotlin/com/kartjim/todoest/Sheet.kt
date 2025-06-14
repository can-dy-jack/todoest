package com.kartjim.todoest

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun SheetContent() {
    val state = rememberDrawerState(
        initialValue = DrawerValue.Closed
    )
    ModalNavigationDrawer(
        drawerContent = {
            Box(
                modifier = Modifier
                    .width(200.dp)
                    .fillMaxHeight()
                    .background(Color.Green)
            )
        },
        drawerState = state
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            val scope = rememberCoroutineScope()
//            Button(
//                onClick = {
//                    scope.launch {
//                        state.open()
//                    }
//
//                }
//            ) {
//                Text("OPEN")
//            }

            val list = remember {
                listOf(
                    WeChatData("laochen", "wwww"),
                    WeChatData("laoch2en", "wwww"),
                    WeChatData("laoc12313hen", "wwww"),
                    WeChatData("laochen", "wwww"),
                    WeChatData("laochen123123", "wwww"),
                    WeChatData("laochen", "ww3333ww"),
                    WeChatData("lao2chen", "wwww"),
                    WeChatData("laoc1hen", "2wwww"),
                )
            }

            LazyVerticalGrid(
                columns = GridCells.Adaptive(300.dp)
            ) {
                items(list) { p ->
                    WeChatItem(p.people, p.message)
                }
            }

//            Column {
//
//                WeChatItem("laowu")
//                WeChatItem("laowan")
//            }
        }
    }
}

@Composable
fun WeChatItem(
    people: String,
    info: String = "no text"
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(Color.Gray)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(text = people)
            Text(text = info, fontSize = 10.sp)
        }
    }
}

data class WeChatData(
    val people: String,
    val message: String
)
