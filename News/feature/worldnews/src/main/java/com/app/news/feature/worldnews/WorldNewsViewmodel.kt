package com.app.news.feature.worldnews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.news.core.domain.WorldNewsUseCase
import com.app.news.core.common.result.NewsResult
import com.app.news.core.network.model.WorldNews
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorldNewsViewmodel @Inject constructor(
    private val worldNewsUseCase: WorldNewsUseCase
) : ViewModel() {
    private val categories = listOf("business", "Entertainment", "technology")
    private val _worldNewsUiState = MutableStateFlow<WorldNewsUiState>(WorldNewsUiState.Loading)
    val worldNewsUiState: StateFlow<WorldNewsUiState> = _worldNewsUiState.asStateFlow()

    init {
        getWorldNews()
    }

    fun getWorldNews() {
        viewModelScope.launch {
            when (val result = worldNewsUseCase(categories)) {
                is NewsResult.Error -> {
                    _worldNewsUiState.value = WorldNewsUiState.Error(result.exception.message ?: "")
                }

                is NewsResult.Loading -> _worldNewsUiState.value = WorldNewsUiState.Loading
                is NewsResult.Success -> {
                    _worldNewsUiState.value = WorldNewsUiState.Success(result.data)
                }
            }
        }
    }
}

sealed interface WorldNewsUiState {
    data class Success(val articles: List<WorldNews>) : WorldNewsUiState
    data class Error(val message: String) : WorldNewsUiState
    data object Loading : WorldNewsUiState
}