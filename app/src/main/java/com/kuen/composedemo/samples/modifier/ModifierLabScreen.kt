package com.kuen.composedemo.samples.modifier

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kuen.composedemo.R
import com.kuen.composedemo.samples.common.SampleScaffold
import com.kuen.composedemo.samples.common.sampleContentPadding

/**
 * Modifier 顺序实验：同一视觉下，链式顺序不同则测量/绘制/命中区域不同。
 * 技术要点见 docs/articles/02-Modifier链与布局顺序.md
 */
@Composable
fun ModifierLabScreen(onBack: () -> Unit) {
    SampleScaffold(
        title = stringResource(R.string.sample_modifier_title),
        onBack = onBack,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .sampleContentPadding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Text(
                text = stringResource(R.string.sample_modifier_intro),
                style = MaterialTheme.typography.bodyLarge,
            )

            Text(text = stringResource(R.string.sample_modifier_case1), style = MaterialTheme.typography.titleSmall)
            // 先 padding 再 background：色块不包含外扩空白
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .padding(16.dp)
                    .background(Color(0xFFB3E5FC))
                    .border(1.dp, Color.DarkGray),
                contentAlignment = Alignment.Center,
            ) {
                Text("A", style = MaterialTheme.typography.titleLarge)
            }

            Text(text = stringResource(R.string.sample_modifier_case2), style = MaterialTheme.typography.titleSmall)
            // 先 background 再 padding：背景铺满 120.dp，文字仍内边距
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .background(Color(0xFFFFCCBC))
                    .padding(16.dp)
                    .border(1.dp, Color.DarkGray),
                contentAlignment = Alignment.Center,
            ) {
                Text("B", style = MaterialTheme.typography.titleLarge)
            }

            Text(text = stringResource(R.string.sample_modifier_case3), style = MaterialTheme.typography.titleSmall)
            // clickable 在 padding 外：整块更易点；若反过来，可点区域会变小
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { /* 仅演示命中区域 */ }
                    .padding(24.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = stringResource(R.string.sample_modifier_clickable_hint),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
    }
}
