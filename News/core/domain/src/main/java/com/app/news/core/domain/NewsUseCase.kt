package com.app.news.core.domain


import com.app.news.core.data.repository.NewsRepository
import com.app.news.core.common.result.NewsResult
import com.app.news.core.network.model.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/*
   * A use case that returns articles and filters out null items from the network result.
   * Changing the first letter of the source name to up
*/
class NewsUseCase @Inject constructor(private val repository: NewsRepository) {

    suspend operator fun invoke(country: String): NewsResult<List<Article>> {
        return withContext(Dispatchers.IO) {
            try {
                val result = repository.getNews(country)
                if (result.status == "ok") {
                    val articles = result.articles.filter { filterItem ->
                        filterItem.source?.let {
                            it.name != null && it.name != "[Removed]"
                        } ?: false
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
                            if (eachItem.urlToImage != null) eachItem.urlToImage else "URL"
                    }
                    NewsResult.Success(articles)
                } else {
                    NewsResult.Error(Exception("Data not found"))
                }
            } catch (e: Exception) {
                NewsResult.Error(e)
            }
        }
    }
}