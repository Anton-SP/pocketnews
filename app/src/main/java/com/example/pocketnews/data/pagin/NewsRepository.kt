package com.example.pocketnews.data.pagin

import androidx.paging.PagingData
import com.example.pocketnews.data.NewsEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class NewsRepository {

    //val newsStream: Flow<PagingData<NewsEntity>> = flowOf()
    fun newsPagingSource() = NewsPagingSource()

}