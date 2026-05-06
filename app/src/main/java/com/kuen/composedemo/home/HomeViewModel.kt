package com.kuen.composedemo.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        HomeUiState(
            headline = "Compose 实践样例",
            counter = 0,
        ),
    )
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.Increment -> _uiState.update { it.copy(counter = it.counter + 1) }
        }
    }
}
