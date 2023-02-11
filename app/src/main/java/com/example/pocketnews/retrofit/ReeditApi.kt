package com.example.pocketnews.retrofit

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ReeditApi {
    /*@GET("popular.json?after={after_key}")
    suspend fun getNews(@Path("after_key") afterKey: String): NewsEntityDto*/

    @GET("popular.json")
    suspend fun getNews(@Query("after") afterKey: String): NewsEntityDto
}