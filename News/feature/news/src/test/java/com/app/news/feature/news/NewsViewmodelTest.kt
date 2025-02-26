package com.app.news.feature.news

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.news.core.domain.NewsUseCase
import com.app.news.core.common.result.NewsResult
import com.app.news.core.network.model.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
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
class NewsViewmodelTest {
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    private val dispatcher = StandardTestDispatcher()

    private val testScope = TestScope(dispatcher)

    @Mock
    private lateinit var newsUseCase: NewsUseCase

    private lateinit var viewmodel: NewsViewmodel

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        newsUseCase = mock(NewsUseCase::class.java)
        viewmodel = NewsViewmodel(newsUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun givenLoading_whenGetNewsInitial_ReturnLoading() = testScope.runTest {
        `when`(newsUseCase("US")).thenReturn(NewsResult.Loading)
        viewmodel.getNews()
        advanceUntilIdle()
        assertEquals(NewsUiState.Loading, viewmodel.newsUiState.value)
    }

    @Test
    fun givenArticleList_whenGetNewsFetchFromServer_ReturnSuccessList() = testScope.runTest {
        val list = listOf(Article(), Article())
        `when`(newsUseCase("US")).thenReturn(NewsResult.Success(list))
        viewmodel.getNews()
        advanceUntilIdle()
        assertEquals(NewsUiState.Success(list), viewmodel.newsUiState.value)
    }

    @Test
    fun givenErrorMessage_whenGetNewsFetchFromServer_ReturnErrorMessage() = testScope.runTest {
        val message = "Data not found"
        `when`(newsUseCase("US")).thenReturn(NewsResult.Error(Exception(message)))
        viewmodel.getNews()
        advanceUntilIdle()
        assertEquals(NewsUiState.Error(message), viewmodel.newsUiState.value)
    }
}