package com.kartjim.todoest

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.moriafly.salt.ui.SaltTheme
import com.moriafly.salt.ui.TitleBar
import com.moriafly.salt.ui.UnstableSaltUiApi
import com.moriafly.salt.ui.ext.safeMainPadding

@OptIn(UnstableSaltUiApi::class)
@Composable
fun Home() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = SaltTheme.colors.background)
            .safeMainPadding()
    ) {
        TitleBar(
            onBack = {
            },
            text = "header",
            showBackBtn = false
        )
    }
}