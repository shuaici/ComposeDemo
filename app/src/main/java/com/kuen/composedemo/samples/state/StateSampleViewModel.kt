package com.kuen.composedemo.samples.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class StateSampleViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(StateSampleUiState(count = 0, lastSavedSummary = null))
    val uiState: StateFlow<StateSampleUiState> = _uiState.asStateFlow()

    private val _effects = Channel<StateSampleEffect>(capacity = Channel.BUFFERED)
    val effects = _effects.receiveAsFlow()

    fun onEvent(event: StateSampleEvent) {
        val (next, effect) = reduceStateSample(_uiState.value, event)
        _uiState.value = next
        if (effect != null) {
            viewModelScope.launch {
                _effects.send(effect)
            }
        }
    }
}
