package com.app.news.feature.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.news.core.domain.NewsUseCase
import com.app.news.core.common.result.NewsResult
import com.app.news.core.network.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewmodel @Inject constructor(
    private val newsUseCase: NewsUseCase
) : ViewModel() {

    private val _newsUiState = MutableStateFlow<NewsUiState>(NewsUiState.Loading)
    val newsUiState: StateFlow<NewsUiState> = _newsUiState.asStateFlow()

    init {
        getNews()
    }

    fun getNews() {
        viewModelScope.launch {
            when (val result = newsUseCase("US")) {
                is NewsResult.Error -> {
                    _newsUiState.value = NewsUiState.Error(result.exception.message ?: "")
                }

                is NewsResult.Loading -> _newsUiState.value = NewsUiState.Loading
                is NewsResult.Success -> {
                    _newsUiState.value = NewsUiState.Success(result.data)
                }
            }
        }
    }
}

sealed interface NewsUiState {
    data class Success(val articles: List<Article>) : NewsUiState
    data class Error(val message: String) : NewsUiState
    data object Loading : NewsUiState
}