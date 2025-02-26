package com.app.news.core.designsystem.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import com.app.news.core.designsystem.theme.mediumGray

@Composable
fun ContentText(text: String) {
    Text(
        text = text,
        color = mediumGray,
        style = MaterialTheme.typography.bodyMedium,
        maxLines = 3,
        overflow = TextOverflow.Ellipsis,
    )
}