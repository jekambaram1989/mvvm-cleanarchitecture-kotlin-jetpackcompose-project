package com.app.news.core.designsystem.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun TitleText(text: String) {
    Text(
        text = text,
        color = Color.Black,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Medium,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}