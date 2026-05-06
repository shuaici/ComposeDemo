package com.kuen.composedemo.samples.stability

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kuen.composedemo.R
import com.kuen.composedemo.samples.common.SampleScaffold
import com.kuen.composedemo.samples.common.sampleContentPadding

/**
 * 用「变化 vs 不变的入参」观察子 `@Composable` 在父级重组时的组合次数差异。
 * 计数通过 [SideEffect]：每次该组合成功提交后回调一次（与重组/布局阶段相关，教学用）。
 */
@Composable
fun StabilityLabScreen(onBack: () -> Unit) {
    var parentTick by remember { mutableIntStateOf(0) }
    val stableLabel = remember { "fixed" }

    SampleScaffold(
        title = stringResource(R.string.sample_stability_title),
        onBack = onBack,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .sampleContentPadding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                text = stringResource(R.string.sample_stability_intro),
                style = MaterialTheme.typography.bodyLarge,
            )
            Button(onClick = { parentTick++ }) {
                Text(stringResource(R.string.sample_stability_bump_parent))
            }
            Text(
                text = stringResource(R.string.sample_stability_parent_tick, parentTick),
                style = MaterialTheme.typography.titleMedium,
            )

            Text(text = stringResource(R.string.sample_stability_bad), style = MaterialTheme.typography.titleSmall)
            Text(
                text = stringResource(R.string.sample_stability_bad_hint),
                style = MaterialTheme.typography.bodySmall,
            )
            LabelChild(label = parentTick.toString())

            Text(text = stringResource(R.string.sample_stability_good), style = MaterialTheme.typography.titleSmall)
            Text(
                text = stringResource(R.string.sample_stability_good_hint, parentTick),
                style = MaterialTheme.typography.bodySmall,
            )
            LabelChild(label = stableLabel)
        }
    }
}

@Composable
private fun LabelChild(label: String) {
    var compositionCount by remember { mutableIntStateOf(0) }
    SideEffect { compositionCount += 1 }

    Column {
        Text(
            text = stringResource(R.string.sample_stability_child_count, compositionCount),
            style = MaterialTheme.typography.bodyLarge,
        )
        Text(text = label, style = MaterialTheme.typography.titleMedium)
    }
}
