package com.example.pocketnews.ui.list

import android.util.Log
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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

val ITEMS_PER_PAGE = 25
class NewsViewModel(
    private val repository: NewsRepo
) : ViewModel() {

   // val stateFlow = MutableStateFlow<NewsListState>(NewsListState.Loading)
    val stateFlow = MutableStateFlow<NewsListState>(NewsListState.Loading)

    val items: Flow<PagingData<NewsEntity>> = Pager(
        config = PagingConfig(pageSize = ITEMS_PER_PAGE, enablePlaceholders = false),
        pagingSourceFactory = {repository.newsPagingSource()}
    ).flow
        .cachedIn(viewModelScope)

    fun getPagingFlow()= items
    fun getStateFlow() = stateFlow.asStateFlow()

    fun getNewsList() {
        viewModelScope.launch {
            try {
                val news = repository.getNews()
                stateFlow.emit(
                    NewsListState.ListSuccess(data = news)
                )
                if (news.isEmpty()) {
                    stateFlow.emit(NewsListState.Error("EMPTY LIST"))
                    Log.d("HAPPY","пустой лист")
                }

            } catch (e: Exception) {
                Log.d("HAPPY","ошибка" + e.message)
                stateFlow.emit(NewsListState.Error("ERROR_DATA"))
            }

        }
    }
    class NewsViewModelFactory(private  val repo: NewsRepo) : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T = NewsViewModel(repo) as T

    }

}