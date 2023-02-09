package com.example.pocketnews.retrofit

import retrofit2.http.GET

interface ReeditApi {
    @GET("popular.json")
    suspend fun getNews(): NewsEntityDto
}