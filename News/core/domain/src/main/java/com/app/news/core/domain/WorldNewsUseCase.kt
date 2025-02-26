package com.app.news.core.domain

import com.app.news.core.data.repository.WorldNewsRepository
import com.app.news.core.common.result.NewsResult
import com.app.news.core.network.model.WorldNews
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/*
 * A use case that returns articles and filters out null items from the network result.
 * Changing the first letter of the source name to uppercase.
*/
class WorldNewsUseCase @Inject constructor(private val repository: WorldNewsRepository) {
    suspend operator fun invoke(categories: List<String>): NewsResult<List<WorldNews>> {
        return withContext(Dispatchers.IO) {
            try {
                val list = mutableListOf<WorldNews>()
                categories.forEach { category ->
                    val result = repository.getWorldNews(category)
                    if (result.status == "ok") {
                        result.category = category.replaceFirstChar(Char::titlecase)
                        list.add(result)
                    } else {
                        NewsResult.Error(Exception("Data not available"))
                    }
                }

                val newsCategories = list.onEach { worldNews ->
                    worldNews.articles.filter { filterItem ->
                        filterItem.title != null && filterItem.title != "[Removed]"
                    }.onEach { eachItem ->
                        eachItem.source?.let {
                            it.name != null && it.name != "[Removed]"
                            it.name!!.uppercase()
                        } ?: false
                        eachItem.title = eachItem.title ?: ""
                        eachItem.description = eachItem.description ?: ""
                        eachItem.author = eachItem.author ?: ""
                        eachItem.content = eachItem.content ?: ""
                        eachItem.urlToImage =
                            if (eachItem.urlToImage.isNullOrEmpty()) "URL" else eachItem.urlToImage

                    }
                }
                NewsResult.Success(newsCategories)
            } catch (e: Exception) {
                NewsResult.Error(e)
            }
        }
    }
}