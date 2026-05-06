package com.kuen.composedemo.samples.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.kuen.composedemo.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SampleScaffold(
    title: String,
    onBack: () -> Unit,
    snackbarHost: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.nav_back),
                        )
                    }
                },
            )
        },
        snackbarHost = snackbarHost,
    ) { innerPadding ->
        content(innerPadding)
    }
}

@Composable
fun Modifier.sampleContentPadding(innerPadding: PaddingValues): Modifier =
    this.padding(innerPadding)
