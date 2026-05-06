package com.kuen.composedemo.samples.sideeffect

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kuen.composedemo.R
import com.kuen.composedemo.samples.common.SampleScaffold
import com.kuen.composedemo.samples.common.sampleContentPadding

@Composable
fun SideEffectSampleScreen(onBack: () -> Unit) {
    var launchKey by rememberSaveable { mutableIntStateOf(0) }
    val log = remember { mutableStateListOf<String>() }

    LaunchedEffect(launchKey) {
        log.add("LaunchedEffect: key=$launchKey @${System.currentTimeMillis() % 100_000}")
    }

    DisposableEffect(Unit) {
        log.add("DisposableEffect: register（离开本屏会触发 dispose）")
        onDispose {
            log.add("DisposableEffect: onDispose")
        }
    }

    SampleScaffold(
        title = stringResource(R.string.sample_side_effect_title),
        onBack = onBack,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .sampleContentPadding(innerPadding)
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(text = stringResource(R.string.sample_side_effect_intro), style = MaterialTheme.typography.bodyLarge)
            Button(onClick = { launchKey++ }) {
                Text(stringResource(R.string.sample_side_effect_bump_key))
            }
            Text(text = stringResource(R.string.sample_side_effect_log_title), style = MaterialTheme.typography.titleMedium)
            log.reversed().forEach { line ->
                Text(text = "• $line", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
