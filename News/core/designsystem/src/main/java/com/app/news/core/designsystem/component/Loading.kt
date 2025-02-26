package com.app.news.core.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.app.news.core.designsystem.resources.Dimens.strokeWidth

@Composable
fun NewsLoading() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.Center)
                .drawBehind {
                    drawCircle(
                        Color.Black,
                        radius = size.width / 2 - strokeWidth.toPx() / 2,
                        style = Stroke(5.dp.toPx())
                    )
                },
            color = Color.LightGray,
            strokeWidth = strokeWidth
        )
    }
}