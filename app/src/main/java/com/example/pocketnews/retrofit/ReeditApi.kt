package com.example.pocketnews.retrofit

import retrofit2.http.GET

interface ReeditApi {
    @GET("popular")
    suspend fun getNews(): List<NewsEntityDto>
}