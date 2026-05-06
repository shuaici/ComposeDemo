package com.kuen.composedemo.samples.state

data class StateSampleUiState(
    val count: Int,
    val lastSavedSummary: String?,
)

sealed interface StateSampleEvent {
    data object Increment : StateSampleEvent
    data object Save : StateSampleEvent
}

sealed interface StateSampleEffect {
    data class ShowSavedSnackbar(val message: String) : StateSampleEffect
}
