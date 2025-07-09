package com.kartjim.todoest.ui.screen.matrix

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.moriafly.salt.ui.Text

@Composable
fun MatrixPage() {
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(5.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .rightBorder(
                        BorderPosition.RIGHT,
                        1.dp,
                        Color.Black
                    )
                    .fillMaxHeight()
                    .weight(1f)
            ) {
                Text("111")
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            ) {
                Text("111")
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth().weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            ) {
                Text("111")
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .rightBorder(
                        BorderPosition.LEFT,
                        1.dp,
                        Color.Black
                    )
            ) {
                Text("111")
            }
        }
    }
}

enum class BorderPosition {
    LEFT,
    RIGHT,
    TOP,
    BOTTOM
}

// 自定义右边框 Modifier
fun Modifier.rightBorder(
    position: BorderPosition,
    width: Dp = 1.dp,
    color: Color = Color.Black
): Modifier = this.then(
    drawBehind {
        val borderWidth = width.toPx()
        drawIntoCanvas { canvas ->
            val paint = Paint().apply {
                this.color = color
                this.strokeWidth = borderWidth
                this.isAntiAlias = true
            }
            val l = if (position == BorderPosition.LEFT) 0f else size.width
            val r = if (position == BorderPosition.RIGHT) 0f else size.width
            val t = if (position == BorderPosition.TOP) 0f else size.width
            val b = if (position == BorderPosition.BOTTOM) 0f else size.width

            if (position == BorderPosition.RIGHT) {
                canvas.drawLine(
                    p1 = Offset(x = size.width, y = 0f),
                    p2 = Offset(x = size.width, y = size.height),
                    paint = paint
                )
            } else if (position == BorderPosition.BOTTOM) {
                canvas.drawLine(
                    p1 = Offset(x = 0f, y = size.height),
                    p2 = Offset(x = size.width, y = size.height),
                    paint = paint
                )
            } else {
                canvas.drawLine(
                    p1 = Offset(x = 0f, y = size.height),
                    p2 = Offset(x = 0f, y = size.height),
                    paint = paint
                )
            }
        }
    }
)
