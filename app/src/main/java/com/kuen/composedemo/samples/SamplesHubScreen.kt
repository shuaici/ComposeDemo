package com.kuen.composedemo.samples

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kuen.composedemo.R
import com.kuen.composedemo.navigation.Routes
import com.kuen.composedemo.samples.common.SampleScaffold
import com.kuen.composedemo.samples.common.sampleContentPadding

/**
 * 学习顺序与 docs/articles 00～08 对齐：状态 → Modifier → 副作用 → 列表 → 导航 → 主题 → 稳定性。
 */
@Composable
fun SamplesHubScreen(
    onBack: () -> Unit,
    onNavigate: (String) -> Unit,
) {
    SampleScaffold(
        title = stringResource(R.string.samples_hub_title),
        onBack = onBack,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .sampleContentPadding(innerPadding)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(
                text = stringResource(R.string.samples_hub_intro),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(vertical = 8.dp),
            )
            SampleNavButton(
                label = stringResource(R.string.sample_state_title),
                onClick = { onNavigate(Routes.STATE_SAMPLE) },
            )
            SampleNavButton(
                label = stringResource(R.string.sample_modifier_title),
                onClick = { onNavigate(Routes.MODIFIER_SAMPLE) },
            )
            SampleNavButton(
                label = stringResource(R.string.sample_side_effect_title),
                onClick = { onNavigate(Routes.SIDE_EFFECT_SAMPLE) },
            )
            SampleNavButton(
                label = stringResource(R.string.sample_lazy_title),
                onClick = { onNavigate(Routes.LAZY_SAMPLE) },
            )
            SampleNavButton(
                label = stringResource(R.string.sample_nav_title),
                onClick = { onNavigate(Routes.profileRoute("demo_user")) },
            )
            SampleNavButton(
                label = stringResource(R.string.sample_design_title),
                onClick = { onNavigate(Routes.DESIGN_SAMPLE) },
            )
            SampleNavButton(
                label = stringResource(R.string.sample_stability_title),
                onClick = { onNavigate(Routes.STABILITY_SAMPLE) },
            )
            SampleNavButton(
                label = stringResource(R.string.sample_controls_title),
                onClick = { onNavigate(Routes.CONTROLS_SAMPLE) },
            )
            SampleNavButton(
                label = stringResource(R.string.sample_custom_layout_title),
                onClick = { onNavigate(Routes.CUSTOM_LAYOUT_SAMPLE) },
            )
            SampleNavButton(
                label = stringResource(R.string.sample_animation_title),
                onClick = { onNavigate(Routes.ANIMATION_SAMPLE) },
            )
            SampleNavButton(
                label = stringResource(R.string.sample_focus_title),
                onClick = { onNavigate(Routes.FOCUS_SAMPLE) },
            )
            Text(
                text = stringResource(R.string.samples_hub_footer_tests),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp),
            )
        }
    }
}

@Composable
private fun SampleNavButton(label: String, onClick: () -> Unit) {
    Button(onClick = onClick, modifier = Modifier.fillMaxWidth()) {
        Text(text = label, style = MaterialTheme.typography.bodyLarge)
    }
}
