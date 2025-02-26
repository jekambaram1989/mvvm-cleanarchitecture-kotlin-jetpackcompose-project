package com.app.news.feature.worldnews

import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.util.fastForEachIndexed
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.news.core.designsystem.component.NewsError
import com.app.news.core.designsystem.component.NewsLoading
import com.app.news.core.designsystem.component.NewsScrollableTabRow
import com.app.news.core.designsystem.resources.Dimens.FILTER_CHIP_INDEX
import com.app.news.core.designsystem.resources.Dimens.PAGER_WEIGHT
import com.app.news.core.designsystem.resources.Dimens.padding
import com.app.news.core.network.model.Article

@Composable
fun WorldNewsScreen(
    innerPaddingValues: PaddingValues,
    viewModel: WorldNewsViewmodel = hiltViewModel(),
    onItemClick: (Article) -> Unit,
) {
    val worldNewsUiState: WorldNewsUiState by viewModel.worldNewsUiState.collectAsStateWithLifecycle()
    WorldNewsScreen(
        innerPaddingValues,
        worldNewsUiState,
        onItemClick
    )
}

@VisibleForTesting
@Composable
internal fun WorldNewsScreen(
    innerPaddingValues: PaddingValues,
    worldNewsUiState: WorldNewsUiState,
    onItemClick: (Article) -> Unit,
) {
    when (worldNewsUiState) {
        is WorldNewsUiState.Error -> NewsError(message = worldNewsUiState.message)
        is WorldNewsUiState.Loading -> NewsLoading()
        is WorldNewsUiState.Success -> {
            Column(
                modifier = Modifier
                    .padding(innerPaddingValues)
                    .padding(padding)
            ) {
                var selectedIndex by remember {
                    mutableIntStateOf(0)
                }
                val pagerState = rememberPagerState {
                    worldNewsUiState.articles.size
                }
                Column(modifier = Modifier.fillMaxSize()) {
                    NewsScrollableTabRow(selectedIndex = selectedIndex) {
                        worldNewsUiState.articles.fastForEachIndexed { tabIndex, item ->
                            FilterChip(modifier = Modifier
                                .wrapContentSize()
                                .zIndex(FILTER_CHIP_INDEX),
                                selected = false,
                                border = null,
                                onClick = { selectedIndex = tabIndex },
                                label = {
                                    Text(
                                        text = item.category,
                                    )
                                })
                        }

                    }
                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(PAGER_WEIGHT),
                        userScrollEnabled = true
                    ) {
                        LaunchedEffect(pagerState) {
                            snapshotFlow { pagerState.currentPage }.collect { currentPage ->
                                selectedIndex = currentPage
                                pagerState.animateScrollToPage(currentPage)
                            }
                        }
                        LazyColumn(modifier = Modifier.padding(top = padding)) {
                            items(worldNewsUiState.articles[selectedIndex].articles.size) { index ->
                                val article =
                                    worldNewsUiState.articles[selectedIndex].articles[index]
                                WorldNewsBody(
                                    index = index,
                                    size = worldNewsUiState.articles.size,
                                    article = article,
                                    onItemClick = onItemClick,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
