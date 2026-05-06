package com.kuen.composedemo.samples.state

/**
 * 纯函数 reducer：便于单测覆盖分支，不依赖 Android 与协程调度器。
 */
internal fun reduceStateSample(
    state: StateSampleUiState,
    event: StateSampleEvent,
): Pair<StateSampleUiState, StateSampleEffect?> =
    when (event) {
        StateSampleEvent.Increment ->
            state.copy(count = state.count + 1) to null

        StateSampleEvent.Save -> {
            val summary = "已同步 count=${state.count}"
            state.copy(lastSavedSummary = summary) to
                StateSampleEffect.ShowSavedSnackbar(message = summary)
        }
    }
