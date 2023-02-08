package com.example.pocketnews.ui.list

import com.example.pocketnews.data.NewsEntity

sealed class NewsListState {
    data class  ListSuccess(val data:List<NewsEntity>) : NewsListState()

    object Loading : NewsListState()

    data class Error(val message: String): NewsListState()
}
