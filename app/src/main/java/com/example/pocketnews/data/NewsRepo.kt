package com.example.pocketnews.data

interface NewsRepo {



   suspend fun getNews(): List<NewsEntity>
}