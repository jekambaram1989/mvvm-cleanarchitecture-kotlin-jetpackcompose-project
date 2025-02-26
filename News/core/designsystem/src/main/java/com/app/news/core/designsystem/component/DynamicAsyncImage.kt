package com.app.news.core.designsystem.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.app.news.core.designsystem.R

@Composable
fun NewsAsyncImage(
    modifier: Modifier,
    url: String
) {
    AsyncImage(
        modifier = modifier,
        model = ImageRequest
            .Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        placeholder = painterResource(id = R.drawable.ic_launcher_background),
        contentScale = ContentScale.Crop,
        contentDescription = stringResource(id = R.string.image)
    )
}