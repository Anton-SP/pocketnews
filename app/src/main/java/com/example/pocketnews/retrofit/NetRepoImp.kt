package com.example.pocketnews.retrofit

import com.example.pocketnews.data.NewsEntity
import com.example.pocketnews.data.NewsRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import kotlin.coroutines.coroutineContext


private val redditApi = Retrofit.Builder()
    .addConverterFactory(SimpleXmlConverterFactory.create())
    .baseUrl("https://www.reddit.com/r/popular/")
    .client(OkHttpClient.Builder().build())
    .build()
    .create(ReeditApi::class.java)


class NetRepoImp : NewsRepo {
    override suspend fun getNews(): List<NewsEntity> = withContext(Dispatchers.IO) {
        redditApi.getNews().map { it.convertDTOtoNewsEntity() }
    }

}