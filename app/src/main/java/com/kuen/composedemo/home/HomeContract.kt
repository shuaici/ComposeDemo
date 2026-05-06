package com.kuen.composedemo.home

/**
 * UiState：只描述界面「当前长什么样」，不放一次性信号（如 Toast、导航命令），
 * 后者更适合用 channel / SingleEvent 或副作用层表达。
 */
data class HomeUiState(
    val headline: String,
    val counter: Int,
)

sealed interface HomeEvent {
    data object Increment : HomeEvent
}
