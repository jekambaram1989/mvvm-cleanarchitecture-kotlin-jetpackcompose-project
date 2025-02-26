package com.app.news.core.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.app.news.core.designsystem.R
import com.app.news.core.designsystem.resources.Dimens.errorIconSize
import com.app.news.core.designsystem.resources.Dimens.widgetSpaceMedium

@Composable
fun NewsError(
    message: String
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(alignment = Alignment.Center)
        ) {
            Icon(
                Icons.Rounded.Info,
                contentDescription = stringResource(id = R.string.image),
                modifier = Modifier
                    .height(errorIconSize)
                    .width(errorIconSize)
                    .align(Alignment.CenterHorizontally),
                tint = Color.Black
            )
            Spacer(modifier = Modifier.height(widgetSpaceMedium))
            Text(
                text = message,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Black,
            )
        }
    }
}