package com.app.news.core.designsystem.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import com.app.news.core.designsystem.theme.liteGray

@Composable
fun SubContentText(text: String) {
    Text(
        text = text,
        color = liteGray,
        style = MaterialTheme.typography.bodySmall,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}