package com.app.news.feature.news

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NewsScreenKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun newsLoading_whenIsLoading_ShowLoading() {
        composeTestRule.setContent {
            NewsScreen(
                innerPaddingValues = PaddingValues.Absolute(),
                newsUiState = NewsUiState.Loading,
                onItemClick = {}
            )
        }
    }
}