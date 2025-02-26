package com.app.news.core.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.news.core.network.NewsNetworkDataSource
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
class NewsRepositoryImplTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    private val dispatcher = StandardTestDispatcher()

    private val testScope = TestScope(dispatcher)

    @Mock
    private lateinit var network: NewsNetworkDataSource

    private lateinit var repository: NewsRepository

    private val news = News("ok", 1, emptyList(), "US")

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        network = mock(NewsNetworkDataSource::class.java)
        repository = NewsRepositoryImpl(network)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun givenNews_WhenGetNewsFetchFromServer_ReturnNews() = testScope.runTest {
        `when`(network.getNews("US")).thenReturn(news)
        val result = repository.getNews("US")
        advanceUntilIdle()
        Assert.assertEquals(result, news)
    }
}