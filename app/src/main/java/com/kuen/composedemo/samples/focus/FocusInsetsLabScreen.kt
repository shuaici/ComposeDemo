package com.kuen.composedemo.samples.focus

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.kuen.composedemo.R
import com.kuen.composedemo.samples.common.SampleScaffold
import com.kuen.composedemo.samples.common.sampleContentPadding

@Composable
fun FocusInsetsLabScreen(onBack: () -> Unit) {
    val first = remember { FocusRequester() }
    val second = remember { FocusRequester() }
    var line1 by remember { mutableStateOf("") }
    var line2 by remember { mutableStateOf("") }

    SampleScaffold(
        title = stringResource(R.string.sample_focus_title),
        onBack = onBack,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .sampleContentPadding(innerPadding)
                .imePadding()
                .padding(24.dp),
        ) {
            Text(text = stringResource(R.string.sample_focus_intro), style = MaterialTheme.typography.bodyLarge)

            OutlinedTextField(
                value = line1,
                onValueChange = { line1 = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(first),
                label = { Text(stringResource(R.string.sample_focus_field1)) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = { second.requestFocus() }),
            )
            Spacer(Modifier.height(12.dp))
            OutlinedTextField(
                value = line2,
                onValueChange = { line2 = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(second),
                label = { Text(stringResource(R.string.sample_focus_field2)) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            )

            Text(
                text = stringResource(R.string.sample_focus_ime_note),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 16.dp),
            )
        }
    }
}
