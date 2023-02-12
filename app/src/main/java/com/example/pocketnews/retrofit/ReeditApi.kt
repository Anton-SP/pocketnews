package com.example.pocketnews.retrofit

import retrofit2.http.GET
import retrofit2.http.Query

interface ReeditApi {

    @GET("popular.json")
    suspend fun getNews(@Query("after") afterKey: String): NewsEntityDto
}