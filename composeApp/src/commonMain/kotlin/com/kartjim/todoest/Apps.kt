package com.kartjim.todoest

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

object AppCount {
    var count by mutableStateOf(1)
}

@Composable
fun AppContent() {
    Column {
        SearchContent(
            onAdd = {
                AppCount.count++
            }
        )

        LazyVerticalGrid(
            GridCells.Adaptive(50.dp)
        ) {
            items(AppCount.count) {
                AppItem(
                    text = it.toString(),
                    modifier = Modifier
                        .animateItem()
                )
            }
        }
    }
}

@Composable
fun AppItem(
    text: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchIcon(
            onClick = {}
        )
        Text(text)
    }
}

@Composable
fun SearchContent(
    onAdd: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(CircleShape)
            .background(Color.White)

    ) {
        SearchIcon(onClick = {})

        Spacer(modifier = Modifier.weight(1f))

        SearchIcon(onClick = {},Color.Blue)
        SearchIcon(onClick = onAdd,Color.Green)
    }
}

@Composable
private fun SearchIcon(
    onClick: () -> Unit,
    color: Color = Color.Red
) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .clickable {
                onClick()
            }
            .clip(CircleShape)
            .size(32.dp)
            .background(color)
    ) {

    }
}

