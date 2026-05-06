package com.kuen.composedemo.navigation

/**
 * 集中维护 route 字符串，避免 feature 间硬编码分散（导航治理的起点）。
 */
object Routes {
    const val HOME = "home"
    const val ABOUT = "about"

    const val SAMPLES_HUB = "samples"
    const val DESIGN_SAMPLE = "sample/design"
    const val STATE_SAMPLE = "sample/state"
    const val LAZY_SAMPLE = "sample/lazy"
    const val SIDE_EFFECT_SAMPLE = "sample/sideeffect"
    const val MODIFIER_SAMPLE = "sample/modifier"
    const val STABILITY_SAMPLE = "sample/stability"
    const val CONTROLS_SAMPLE = "sample/controls"
    const val CUSTOM_LAYOUT_SAMPLE = "sample/custom_layout"
    const val ANIMATION_SAMPLE = "sample/animation"
    const val FOCUS_SAMPLE = "sample/focus"

    const val ARG_USER_ID = "userId"
    const val PROFILE_WITH_ARGS = "sample/profile/{$ARG_USER_ID}"

    fun profileRoute(userId: String): String = "sample/profile/$userId"
}
