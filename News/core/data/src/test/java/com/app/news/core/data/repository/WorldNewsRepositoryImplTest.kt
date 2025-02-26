package com.app.news.core.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.news.core.network.NewsNetworkDataSource
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
class WorldNewsRepositoryImplTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    private val dispatcher = StandardTestDispatcher()

    private val testScope = TestScope(dispatcher)

    @Mock
    private lateinit var network: NewsNetworkDataSource

    private lateinit var repository: WorldNewsRepository

    private val worldNews = WorldNews("ok", 1, mutableListOf(), "US")

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        network = mock(NewsNetworkDataSource::class.java)
        repository = WorldNewsRepositoryImpl(network)
    }

    @After
    fun teatDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun givenNews_WhenGetNewsFetchFromServer_ReturnNews() = testScope.runTest {
        `when`(network.getWorldNews("US")).thenReturn(worldNews)
        val result = repository.getWorldNews("US")
        advanceUntilIdle()
        Assert.assertEquals(result, worldNews)
    }
}