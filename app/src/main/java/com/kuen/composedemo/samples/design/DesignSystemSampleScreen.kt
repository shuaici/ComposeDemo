package com.kuen.composedemo.samples.design

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kuen.composedemo.R
import com.kuen.composedemo.core.ui.designsystem.ComposeDemoTokens
import com.kuen.composedemo.samples.common.SampleScaffold
import com.kuen.composedemo.samples.common.sampleContentPadding

/**
 * 截图对比：左为 Token，右为反例硬编码（仅用于对照，勿复制到业务）。
 */
@Composable
fun DesignSystemSampleScreen(onBack: () -> Unit) {
    SampleScaffold(
        title = stringResource(R.string.sample_design_title),
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
                text = stringResource(R.string.sample_design_body),
                style = MaterialTheme.typography.bodyLarge,
            )
            Text(
                text = stringResource(R.string.sample_design_token_label),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(ComposeDemoTokens.emphasisContainer)
                    .padding(16.dp),
                color = ComposeDemoTokens.onEmphasisContainer,
            )
            Text(
                text = stringResource(R.string.sample_design_hardcoded_label),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFE8F5E9))
                    .padding(16.dp),
                color = Color(0xFF1B5E20),
            )
        }
    }
}
