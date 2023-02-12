package com.example.pocketnews.data

import com.example.pocketnews.data.pagin.NewsPagingSource

interface NewsRepo {



   suspend fun getNews(): List<NewsEntity>

   fun newsPagingSource() = NewsPagingSource()
}