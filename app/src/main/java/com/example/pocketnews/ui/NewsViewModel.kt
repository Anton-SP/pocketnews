package com.example.pocketnews.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.pocketnews.data.NewsRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewsViewModel(
    private val repository: NewsRepo
) : ViewModel() {

    val stateFlow = MutableStateFlow<NewsListState>(NewsListState.Loading)

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
                }

            } catch (e: Exception) {
                stateFlow.emit(NewsListState.Error("ERROR_DATA"))
            }

        }
    }
    class NewsViewModelFactory(private  val repo: NewsRepo) : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T = NewsViewModel(repo) as T

    }

}