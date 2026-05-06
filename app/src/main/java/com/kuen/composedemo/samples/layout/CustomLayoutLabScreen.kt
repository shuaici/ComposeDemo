package com.kuen.composedemo.samples.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kuen.composedemo.R
import com.kuen.composedemo.samples.common.SampleScaffold
import com.kuen.composedemo.samples.common.sampleContentPadding

/**
 * 最小自定义 [Layout]：横向串联子项（类似简化版 Row），用于理解 measure / place 两阶段。
 */
@Composable
fun CustomLayoutLabScreen(onBack: () -> Unit) {
    SampleScaffold(
        title = stringResource(R.string.sample_custom_layout_title),
        onBack = onBack,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .sampleContentPadding(innerPadding)
                .padding(24.dp),
        ) {
            Text(text = stringResource(R.string.sample_custom_layout_intro), style = MaterialTheme.typography.bodyLarge)
            Text(text = stringResource(R.string.sample_custom_layout_caption), style = MaterialTheme.typography.bodySmall)

            HorizontalTapeLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
            ) {
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .padding(12.dp),
                ) { Text("A") }
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                        .padding(12.dp),
                ) { Text("B") }
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.tertiaryContainer)
                        .padding(12.dp),
                ) { Text("C") }
            }
        }
    }
}

@Composable
private fun HorizontalTapeLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Layout(modifier = modifier, content = content) { measurables, constraints ->
        if (measurables.isEmpty()) {
            return@Layout layout(0, 0) {}
        }
        val childConstraints = constraints.copy(minWidth = 0, minHeight = 0)
        val placeables = measurables.map { it.measure(childConstraints) }
        val width = placeables.sumOf { it.width }.coerceIn(constraints.minWidth, constraints.maxWidth)
        val height = placeables.maxOf { it.height }.coerceIn(constraints.minHeight, constraints.maxHeight)
        layout(width, height) {
            var x = 0
            for (placeable in placeables) {
                placeable.placeRelative(x, 0)
                x += placeable.width
            }
        }
    }
}
