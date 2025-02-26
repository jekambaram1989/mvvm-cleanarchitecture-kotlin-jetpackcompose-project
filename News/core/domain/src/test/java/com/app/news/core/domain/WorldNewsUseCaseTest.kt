package com.app.news.core.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.news.core.data.repository.WorldNewsRepository
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
class WorldNewsUseCaseTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    private val dispatcher = StandardTestDispatcher()

    private val testScope = TestScope(dispatcher)

    @Mock
    private lateinit var repository: WorldNewsRepository

    private lateinit var worldNewsUseCase: WorldNewsUseCase

    private val categories = listOf("business", "entertainment", "technology")

    private val worldNews = listOf(
        WorldNews("ok", 1, mutableListOf(), "business"),
        WorldNews("ok", 1, mutableListOf(), "entertainment"),
        WorldNews("ok", 1, mutableListOf(), "technology")
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        repository = mock(WorldNewsRepository::class.java)
        worldNewsUseCase = WorldNewsUseCase(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun givenCategories_whenWorldNewsFetchFromServer_ReturnNetworkResultSuccessWithList() =
        testScope.runTest {
            categories.forEachIndexed { index, category ->
                `when`(repository.getWorldNews(category)).thenReturn(worldNews[index])
            }
            val result = worldNewsUseCase(categories)
            advanceUntilIdle()
            assertEquals(result, NewsResult.Success(worldNews))
        }
}