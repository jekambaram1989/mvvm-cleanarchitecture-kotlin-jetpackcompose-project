package com.app.news.feature.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.app.news.core.designsystem.component.NewsAsyncImage
import com.app.news.core.designsystem.resources.Dimens.SINGLE_LINE
import com.app.news.core.designsystem.resources.Dimens.bottomPadding
import com.app.news.core.designsystem.resources.Dimens.iconSize
import com.app.news.core.designsystem.resources.Dimens.imageSize
import com.app.news.core.designsystem.resources.Dimens.padding
import com.app.news.core.designsystem.resources.Dimens.widgetSpaceSmall
import com.app.news.core.common.result.util.toLocalDate
import com.app.news.core.network.model.Article

@Composable
fun DetailsScreen(
    innerPadding: PaddingValues,
    article: Article,
    onClick: () -> Unit
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .padding(innerPadding)
    ) {
        Spacer(modifier = Modifier.padding(bottom = padding))
        Icon(
            imageVector = Icons.AutoMirrored.Default.ArrowBack,
            contentDescription = stringResource(id = R.string.back),
            tint = Color.Black,
            modifier = Modifier
                .padding(start = padding)
                .height(iconSize)
                .width(iconSize)
                .clickable(onClick = onClick)
        )
        Spacer(modifier = Modifier.padding(bottom = padding))
        Column(
            modifier = Modifier.verticalScroll(scrollState)
        ) {
            Text(
                modifier = Modifier.padding(start = padding, end = padding),
                text = article.title!!,
                color = Color.Black,
                fontWeight = FontWeight.ExtraBold,
                style = MaterialTheme.typography.bodyLarge,
            )
            Spacer(modifier = Modifier.padding(bottom = padding))
            Text(
                modifier = Modifier.padding(start = padding, end = padding),
                text = article.description!!,
                color = Color.Black,
                style = MaterialTheme.typography.bodyLarge,
            )
            Spacer(modifier = Modifier.padding(bottom = bottomPadding))
            Text(
                modifier = Modifier.padding(start = padding, end = padding),
                text = "by ${article.author}",
                style = MaterialTheme.typography.bodyLarge,
                maxLines = SINGLE_LINE,
                color = Color.Gray,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.padding(bottom = widgetSpaceSmall))
            Text(
                modifier = Modifier.padding(start = padding, end = padding),
                text = article.publishedAt!!.toLocalDate(),
                style = MaterialTheme.typography.bodyLarge,
                maxLines = SINGLE_LINE,
                color = Color.Gray,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.padding(bottom = bottomPadding))
            NewsAsyncImage(
                url = article.urlToImage!!,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(imageSize),
            )

            Spacer(modifier = Modifier.padding(bottom = bottomPadding))
            Text(
                modifier = Modifier.padding(start = padding, end = padding),
                text = article.content!!,
                color = Color.Black,
                style = MaterialTheme.typography.bodyLarge,
            )
            Spacer(modifier = Modifier.padding(bottom = bottomPadding))
        }
    }
}