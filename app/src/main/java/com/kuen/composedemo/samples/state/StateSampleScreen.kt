package com.kuen.composedemo.samples.state

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kuen.composedemo.R
import com.kuen.composedemo.samples.common.SampleScaffold
import com.kuen.composedemo.samples.common.sampleContentPadding

@Composable
fun StateSampleScreen(
    onBack: () -> Unit,
    viewModel: StateSampleViewModel = viewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                is StateSampleEffect.ShowSavedSnackbar ->
                    snackbarHostState.showSnackbar(effect.message)
            }
        }
    }

    SampleScaffold(
        title = stringResource(R.string.sample_state_title),
        onBack = onBack,
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .sampleContentPadding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                text = stringResource(R.string.sample_state_body),
                style = MaterialTheme.typography.bodyLarge,
            )
            Text(
                text = stringResource(R.string.sample_state_count, state.count),
                style = MaterialTheme.typography.titleLarge,
            )
            state.lastSavedSummary?.let { summary ->
                Text(text = summary, style = MaterialTheme.typography.bodyMedium)
            }
            Button(onClick = { viewModel.onEvent(StateSampleEvent.Increment) }) {
                Text(stringResource(R.string.sample_state_increment))
            }
            Button(onClick = { viewModel.onEvent(StateSampleEvent.Save) }) {
                Text(stringResource(R.string.sample_state_save))
            }
        }
    }
}
