package com.app.news.feature.worldnews

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.news.core.domain.WorldNewsUseCase
import com.app.news.core.common.result.NewsResult
import com.app.news.core.network.model.WorldNews
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class WorldNewsViewmodelTest {
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    private val dispatcher = StandardTestDispatcher()

    private val testScope = TestScope(dispatcher)

    @Mock
    private lateinit var worldNewsUseCase: WorldNewsUseCase

    private lateinit var viewmodel: WorldNewsViewmodel

    private val categories = listOf("business", "Entertainment", "technology")

    private val worldNews = listOf(
        WorldNews("ok", 1, mutableListOf(), "business"),
        WorldNews("ok", 1, mutableListOf(), "entertainment"),
        WorldNews("ok", 1, mutableListOf(), "technology")
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        worldNewsUseCase = mock(WorldNewsUseCase::class.java)
        viewmodel = WorldNewsViewmodel(worldNewsUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun givenLoading_whenGetWorldNewsInitial_ReturnLoading() = testScope.runTest {
        `when`(worldNewsUseCase(categories)).thenReturn(NewsResult.Loading)
        viewmodel.getWorldNews()
        advanceUntilIdle()
        assertEquals(WorldNewsUiState.Loading, viewmodel.worldNewsUiState.value)
    }

    @Test
    fun givenWorldNews_WhenGetWorldNewsFetchFromServer_ReturnSuccessList() = testScope.runTest {
        `when`(worldNewsUseCase(categories)).thenReturn(NewsResult.Success(worldNews))
        viewmodel.getWorldNews()
        advanceUntilIdle()
        assertEquals(WorldNewsUiState.Success(worldNews), viewmodel.worldNewsUiState.value)
    }

    @Test
    fun givenErrorMessage_WhenGetWorldNewsFetchFromServer_ReturnError() = testScope.runTest {
        val message = "Data not found"
        `when`(worldNewsUseCase(categories)).thenReturn(NewsResult.Error(Exception(message)))
        viewmodel.getWorldNews()
        advanceUntilIdle()
        assertEquals(WorldNewsUiState.Error(message), viewmodel.worldNewsUiState.value)
    }
}