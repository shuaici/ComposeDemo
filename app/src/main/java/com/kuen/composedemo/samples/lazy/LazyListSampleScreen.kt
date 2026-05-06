package com.kuen.composedemo.samples.lazy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kuen.composedemo.R
import com.kuen.composedemo.samples.common.SampleScaffold
import com.kuen.composedemo.samples.common.sampleContentPadding

private data class DemoRow(
    val id: Int,
    val title: String,
)

@Composable
fun LazyListSampleScreen(onBack: () -> Unit) {
    val rows = remember {
        List(40) { index -> DemoRow(id = index, title = "Item #$index") }
    }

    SampleScaffold(
        title = stringResource(R.string.sample_lazy_title),
        onBack = onBack,
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .sampleContentPadding(innerPadding)
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            item {
                Text(
                    text = stringResource(R.string.sample_lazy_intro),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 8.dp),
                )
            }
            items(items = rows, key = { it.id }) { row ->
                Text(
                    text = row.title,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                )
            }
        }
    }
}
