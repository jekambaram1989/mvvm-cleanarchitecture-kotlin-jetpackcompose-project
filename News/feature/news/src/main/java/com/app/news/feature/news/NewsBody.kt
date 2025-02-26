package com.app.news.feature.news

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.app.news.core.designsystem.component.ContentText
import com.app.news.core.designsystem.component.NewsAsyncImage
import com.app.news.core.designsystem.component.SubContentText
import com.app.news.core.designsystem.component.TitleText
import com.app.news.core.designsystem.resources.Dimens.NEWS_ITEM_IMAGE_WEIGHT
import com.app.news.core.designsystem.resources.Dimens.NEWS_ITEM_WEIGHT
import com.app.news.core.designsystem.resources.Dimens.bottomPadding
import com.app.news.core.designsystem.resources.Dimens.dividerThickness
import com.app.news.core.designsystem.resources.Dimens.newsImageSize
import com.app.news.core.designsystem.resources.Dimens.padding
import com.app.news.core.designsystem.resources.Dimens.rounderCorner
import com.app.news.core.designsystem.resources.Dimens.widgetSpaceSmall
import com.app.news.core.common.result.util.toLocalDate
import com.app.news.core.network.model.Article

@Composable
internal fun NewsBody(
    index: Int,
    size: Int,
    article: Article,
    onItemClick: (Article) -> Unit,
) {
    val isLastItem = remember {
        mutableStateOf(
            index != size - 1
        )
    }
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top,
        modifier = Modifier.clickable(onClick = {
            onItemClick(article)
        })
    ) {
        Column(
            modifier = Modifier
                .weight(NEWS_ITEM_WEIGHT)
                .padding(bottom = padding, end = padding)
        ) {
            TitleText(text = article.source?.let { it.name ?: "" } ?: "")
            Spacer(modifier = Modifier.padding(top = widgetSpaceSmall))
            ContentText(text = article.title ?: "")
            Spacer(modifier = Modifier.padding(top = widgetSpaceSmall))
            SubContentText(text = article.publishedAt?.toLocalDate() ?: "")
        }
        NewsAsyncImage(
            modifier = Modifier
                .weight(NEWS_ITEM_IMAGE_WEIGHT)
                .height(newsImageSize)
                .clip(RoundedCornerShape(rounderCorner)),
            url = article.urlToImage ?: "",
        )
    }
    if (isLastItem.value) {
        HorizontalDivider(
            color = Color.LightGray,
            thickness = dividerThickness,
            modifier = Modifier.padding(bottom = bottomPadding)
        )
    }
}