package com.kartjim.todoest.ui.screen.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.moriafly.salt.ui.Button
import com.moriafly.salt.ui.Text
import com.moriafly.salt.ui.ext.safeMainPadding
import com.moriafly.salt.ui.outerPadding
import kotlin.random.Random

@Composable
fun MainScreen(
    viewModel: MainViewModel = viewModel { MainViewModel() }
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeMainPadding()
    ) {
        Row(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth()
        ) {
            Button(
                onClick = {
                    viewModel.insert(Random.nextInt().toString())
                },
                text = "Add Todo"
            )
        }

        val todos by viewModel.todos.collectAsState()
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(todos) { todo ->
                Column(
                    modifier = Modifier
                        .outerPadding()

                ) {
                    Text(
                        text = "Id = ${todo.id}",

                    )
                    Text(
                        text = "Title = ${todo.title}"
                    )
                }
            }
        }
    }
}
