package com.kuen.composedemo.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kuen.composedemo.R
import com.kuen.composedemo.core.ui.designsystem.ComposeDemoTheme

@Composable
fun HomeRoute(
    onOpenAbout: () -> Unit,
    onOpenSamples: () -> Unit,
    viewModel: HomeViewModel = viewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    HomeScreen(
        state = state,
        onEvent = viewModel::onEvent,
        onOpenAbout = onOpenAbout,
        onOpenSamples = onOpenSamples,
    )
}

@Composable
fun HomeScreen(
    state: HomeUiState,
    onEvent: (HomeEvent) -> Unit,
    onOpenAbout: () -> Unit,
    onOpenSamples: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = state.headline, style = MaterialTheme.typography.titleLarge)
        Text(
            text = stringResource(R.string.home_counter, state.counter),
            style = MaterialTheme.typography.bodyLarge,
        )
        Button(onClick = { onEvent(HomeEvent.Increment) }) {
            Text(text = stringResource(R.string.home_increment))
        }
        Button(onClick = onOpenAbout) {
            Text(text = stringResource(R.string.home_open_about))
        }
        Button(onClick = onOpenSamples) {
            Text(text = stringResource(R.string.home_open_samples))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    ComposeDemoTheme {
        HomeScreen(
            state = HomeUiState(headline = "预览", counter = 3),
            onEvent = {},
            onOpenAbout = {},
            onOpenSamples = {},
        )
    }
}
