package com.app.news.core.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.news.core.data.repository.NewsRepository
import com.app.news.core.common.result.NewsResult
import com.app.news.core.network.model.Article
import com.app.news.core.network.model.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
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
class NewsUseCaseTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    private val dispatcher = StandardTestDispatcher()

    private val testScope = TestScope(dispatcher)

    @Mock
    private lateinit var repository: NewsRepository

    private lateinit var newsUseCase: NewsUseCase

    private val news = News("ok", 1, emptyList(), "US")

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        repository = mock(NewsRepository::class.java)
        newsUseCase = NewsUseCase(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun givenNews_WhenGetNewsFetchFromServer_ReturnNewsResultSuccessWithList() = testScope.runTest {
        `when`(repository.getNews("us")).thenReturn(news)
        val result = newsUseCase("us")
        advanceUntilIdle()
        Assert.assertEquals(result, NewsResult.Success<List<Article>>(emptyList()))
    }
}