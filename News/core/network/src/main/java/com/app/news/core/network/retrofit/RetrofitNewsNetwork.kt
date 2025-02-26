package com.app.news.core.network.retrofit

import com.app.news.core.network.NewsNetworkDataSource
import com.app.news.core.network.model.News
import com.app.news.core.network.model.WorldNews
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

private const val BASE_URL = "https://newsapi.org/v2/"
private const val HEADLINES = "top-headlines"
private const val QUERY_COUNTRY = "country"
private const val QUERY_CATEGORY = "category"

private interface RetrofitNetworkApi {

    @GET(value = HEADLINES)
    suspend fun getNews(@Query(QUERY_COUNTRY) country: String): News

    @GET(value = HEADLINES)
    suspend fun getWorldNews(@Query(QUERY_CATEGORY) category: String): WorldNews
}


internal class RetrofitNewsNetwork @Inject constructor(okHttpClient: OkHttpClient) :
    NewsNetworkDataSource {

    private val retrofitNetworkApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitNetworkApi::class.java)

    override suspend fun getNews(country: String): News =
        retrofitNetworkApi.getNews(country)

    override suspend fun getWorldNews(category: String): WorldNews =
        retrofitNetworkApi.getWorldNews(category)
}