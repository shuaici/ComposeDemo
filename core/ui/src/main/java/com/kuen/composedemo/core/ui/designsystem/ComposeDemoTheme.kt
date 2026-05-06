package com.kuen.composedemo.core.ui.designsystem

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color

/**
 * 设计系统入口：业务/feature 模块只应依赖这里的 Theme 与 Token，
 * 避免在业务里散落硬编码色值与 Typography（对应系列文「Compose 在企业里的边界」）。
 */
@Composable
fun ComposeDemoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val scheme = if (darkTheme) ComposeDemoDarkColors else ComposeDemoLightColors
    MaterialTheme(
        colorScheme = scheme,
        typography = ComposeDemoTypography,
        content = content,
    )
}

object ComposeDemoTokens {
    val success: Color
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.colorScheme.tertiary

    /** 强调块、提示条背景：来自语义色而非业务硬编码。 */
    val emphasisContainer: Color
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.colorScheme.primaryContainer

    val onEmphasisContainer: Color
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.colorScheme.onPrimaryContainer
}

private val ComposeDemoLightColors = lightColorScheme(
    primary = Color(0xFF1565C0),
    onPrimary = Color.White,
    secondary = Color(0xFF5C6BC0),
    tertiary = Color(0xFF2E7D32),
)

private val ComposeDemoDarkColors = darkColorScheme(
    primary = Color(0xFF90CAF9),
    onPrimary = Color(0xFF0D47A1),
    secondary = Color(0xFF9FA8DA),
    tertiary = Color(0xFFA5D6A7),
)
