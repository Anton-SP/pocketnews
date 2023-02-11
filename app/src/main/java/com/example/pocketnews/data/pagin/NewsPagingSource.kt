package com.example.pocketnews.data.pagin

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pocketnews.data.NewsEntity
import com.example.pocketnews.retrofit.ReeditApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val STARTING_KEY = "start"

private val redditApi = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl("https://www.reddit.com/r/")
    .client(OkHttpClient.Builder().build())
    .build()
    .create(ReeditApi::class.java)


class NewsPagingSource : PagingSource<String, NewsEntity>() {

    override fun getRefreshKey(state: PagingState<String, NewsEntity>): String? {
        val anchorPosition = state.anchorPosition ?: return null
        val news = state.closestItemToPosition(anchorPosition) ?: return null
        return news.name
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, NewsEntity> {
        val start = params.key ?: STARTING_KEY

        val newsDTO = redditApi.getNews(start)


        return LoadResult.Page(
            data = newsDTO.data.children.map {
                NewsEntity(
                    it.data.author,
                    it.data.title,
                    it.data.name
                )
            },
           /* nextKey = when (start) {
                STARTING_KEY -> newsDTO.data.after
                else -> newsDTO.data.after
            },*/
            nextKey = newsDTO.data.after,
            prevKey = null
        )

    }


}