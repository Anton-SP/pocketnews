package com.example.pocketnews.ui.list


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.pocketnews.data.NewsEntity
import com.example.pocketnews.data.NewsRepo
import kotlinx.coroutines.flow.Flow


val ITEMS_PER_PAGE = 25

class NewsViewModel(
    private val repository: NewsRepo
) : ViewModel() {

    val items: Flow<PagingData<NewsEntity>> = Pager(
        config = PagingConfig(pageSize = ITEMS_PER_PAGE, enablePlaceholders = false),
        pagingSourceFactory = { repository.newsPagingSource() }
    ).flow
        .cachedIn(viewModelScope)

    fun getPagingFlow() = items

    class NewsViewModelFactory(private val repo: NewsRepo) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T = NewsViewModel(repo) as T

    }

}