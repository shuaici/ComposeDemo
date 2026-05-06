package com.kuen.composedemo.samples.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kuen.composedemo.R
import com.kuen.composedemo.samples.common.SampleScaffold
import com.kuen.composedemo.samples.common.sampleContentPadding

@Composable
fun ProfileNavSampleScreen(
    userId: String,
    onBack: () -> Unit,
) {
    SampleScaffold(
        title = stringResource(R.string.sample_nav_title),
        onBack = onBack,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .sampleContentPadding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(text = stringResource(R.string.sample_nav_arg_label), style = MaterialTheme.typography.titleMedium)
            Text(
                text = userId.ifBlank { stringResource(R.string.sample_nav_arg_empty) },
                style = MaterialTheme.typography.headlineSmall,
            )
            Text(
                text = stringResource(R.string.sample_nav_hint),
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}
