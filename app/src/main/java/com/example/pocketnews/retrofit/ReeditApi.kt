package com.example.pocketnews.retrofit

import retrofit2.http.GET

interface ReeditApi {
    @GET(".rss")
    suspend fun getNews(): List<NewsEntityDto>
}