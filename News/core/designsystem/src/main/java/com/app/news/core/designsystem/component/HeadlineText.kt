package com.app.news.core.designsystem.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun HeadlineText(id: Int) {
    Text(
        text = stringResource(id = id),
        color = Color.Black,
        textAlign = TextAlign.Left,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.headlineLarge,
    )
}