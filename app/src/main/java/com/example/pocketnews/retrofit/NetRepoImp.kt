package com.example.pocketnews.retrofit

import com.example.pocketnews.data.NewsEntity
import com.example.pocketnews.data.NewsRepo
import com.example.pocketnews.data.pagin.NewsPagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private val redditApi = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl("https://www.reddit.com/r/")
    .client(OkHttpClient.Builder().build())
    .build()
    .create(ReeditApi::class.java)


class NetRepoImp : NewsRepo {


    override suspend fun getNews(): List<NewsEntity> = withContext(Dispatchers.IO){
        redditApi.getNews("start").data.children.map { NewsEntity(it.data.author, it.data.title,it.data.name) }
    }
    override fun newsPagingSource() = NewsPagingSource()

}
