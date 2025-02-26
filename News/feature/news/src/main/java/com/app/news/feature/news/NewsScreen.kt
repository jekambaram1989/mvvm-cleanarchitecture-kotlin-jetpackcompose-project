package com.app.news.feature.news

import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.news.core.designsystem.component.HeadlineText
import com.app.news.core.designsystem.component.NewsError
import com.app.news.core.designsystem.component.NewsLoading
import com.app.news.core.designsystem.resources.Dimens.padding
import com.app.news.core.designsystem.resources.Dimens.topPadding
import com.app.news.core.network.model.Article

@Composable
fun NewsScreen(
    innerPaddingValues: PaddingValues,
    viewModel: NewsViewmodel = hiltViewModel(),
    onItemClick: (Article) -> Unit,
) {
    val newsUiState: NewsUiState by viewModel.newsUiState.collectAsStateWithLifecycle()
    NewsScreen(
        newsUiState = newsUiState,
        innerPaddingValues = innerPaddingValues,
        onItemClick = onItemClick
    )
}

@VisibleForTesting
@Composable
internal fun NewsScreen(
    innerPaddingValues: PaddingValues,
    newsUiState: NewsUiState,
    onItemClick: (Article) -> Unit,
) {
    when (newsUiState) {
        is NewsUiState.Error -> NewsError(message = newsUiState.message)
        is NewsUiState.Loading -> NewsLoading()
        is NewsUiState.Success -> {
            Column(
                modifier = Modifier
                    .padding(innerPaddingValues)
                    .padding(padding)
            ) {
                HeadlineText(id = R.string.news)
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = topPadding)
                ) {
                    items(newsUiState.articles.size) { index ->
                        NewsBody(
                            index = index,
                            size = newsUiState.articles.size,
                            article = newsUiState.articles[index],
                            onItemClick = onItemClick
                        )
                    }
                }
            }
        }
    }
}