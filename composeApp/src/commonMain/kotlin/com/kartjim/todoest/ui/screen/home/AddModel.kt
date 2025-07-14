package com.kartjim.todoest.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddModel(
    modifier: Modifier = Modifier
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )

    FloatingActionButton(
        onClick = {
            showBottomSheet = true
        },
        modifier = modifier
    ) {
        Icon(Icons.Filled.Add, "新增")
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            modifier = Modifier.fillMaxHeight(),
            sheetState = sheetState,
            onDismissRequest = {
                showBottomSheet = false
            }
        ) {
            var text by remember { mutableStateOf("") }

            Column(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            ) {
                TextField(
                    value = text,
                    onValueChange = { value ->
                        text = value
                    },
//                    label = { Text("请输入内容") },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Text(
                    "Swipe up to open sheet. Swipe down to dismiss.",
                )
            }
        }
    }
}