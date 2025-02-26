package com.app.news.feature.worldnews

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WorldNewsScreenKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun worldNewsLoading_whenIsLoading_showLoading() {
        composeTestRule.setContent {
            WorldNewsScreen(
                innerPaddingValues = PaddingValues.Absolute(),
                worldNewsUiState = WorldNewsUiState.Loading,
                onItemClick = {})
        }
    }

}