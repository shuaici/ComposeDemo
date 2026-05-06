package com.kuen.composedemo.samples.controls

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.kuen.composedemo.R
import com.kuen.composedemo.samples.common.SampleScaffold
import com.kuen.composedemo.samples.common.sampleContentPadding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Material3 常用控件集合：Text / TextField / Button / Switch / Chip / Radio / Checkbox / Slider / AlertDialog。
 * 与 docs/articles/09 对应；生产提交逻辑请下沉 ViewModel。
 */
@Composable
fun ControlsLabScreen(onBack: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    var loading by remember { mutableStateOf(false) }
    var agree by remember { mutableStateOf(false) }
    var filterOn by remember { mutableStateOf(false) }
    var radioIndex by remember { mutableIntStateOf(0) }
    var checkboxExtra by remember { mutableStateOf(false) }
    var sliderValue by remember { mutableFloatStateOf(0.35f) }
    var showDialog by remember { mutableStateOf(false) }
    val nameTooShort = name.isNotEmpty() && name.length < 2
    val scope = rememberCoroutineScope()

    SampleScaffold(
        title = stringResource(R.string.sample_controls_title),
        onBack = onBack,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .sampleContentPadding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(text = stringResource(R.string.sample_controls_intro), style = MaterialTheme.typography.bodyLarge)

            Text(text = stringResource(R.string.sample_controls_text_section), style = MaterialTheme.typography.titleSmall)
            Text(
                text = stringResource(R.string.sample_controls_long_text),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium,
            )

            HorizontalDivider()

            Text(text = stringResource(R.string.sample_controls_field_section), style = MaterialTheme.typography.titleSmall)
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(stringResource(R.string.sample_controls_name_label)) },
                singleLine = true,
                isError = nameTooShort,
                supportingText = {
                    if (nameTooShort) {
                        Text(stringResource(R.string.sample_controls_name_error))
                    }
                },
                trailingIcon = {
                    if (name.isNotEmpty()) {
                        IconButton(onClick = { name = "" }) {
                            Icon(Icons.Default.Clear, contentDescription = stringResource(R.string.sample_controls_clear))
                        }
                    }
                },
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(stringResource(R.string.sample_controls_password_label)) },
                singleLine = true,
                visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    TextButton(onClick = { showPassword = !showPassword }) {
                        Text(
                            if (showPassword) {
                                stringResource(R.string.sample_controls_hide_pw)
                            } else {
                                stringResource(R.string.sample_controls_show_pw)
                            },
                        )
                    }
                },
            )

            Text(text = stringResource(R.string.sample_controls_button_section), style = MaterialTheme.typography.titleSmall)
            if (loading) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }
            Button(
                onClick = {
                    scope.launch {
                        loading = true
                        delay(600)
                        loading = false
                    }
                },
                enabled = name.length >= 2 && agree && !loading,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(stringResource(R.string.sample_controls_submit))
            }

            Text(text = stringResource(R.string.sample_controls_switch_section), style = MaterialTheme.typography.titleSmall)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Switch(checked = agree, onCheckedChange = { agree = it })
                Text(stringResource(R.string.sample_controls_agree))
            }

            Text(text = stringResource(R.string.sample_controls_chip_section), style = MaterialTheme.typography.titleSmall)
            Text(text = stringResource(R.string.sample_controls_chip_body), style = MaterialTheme.typography.bodySmall)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                AssistChip(onClick = { }, label = { Text(stringResource(R.string.sample_controls_assist_label)) })
                FilterChip(
                    selected = filterOn,
                    onClick = { filterOn = !filterOn },
                    label = { Text(stringResource(R.string.sample_controls_filter_label)) },
                    leadingIcon = if (filterOn) {
                        { Icon(Icons.Default.Check, contentDescription = null) }
                    } else {
                        null
                    },
                )
            }

            Text(text = stringResource(R.string.sample_controls_radio_section), style = MaterialTheme.typography.titleSmall)
            Text(text = stringResource(R.string.sample_controls_radio_hint), style = MaterialTheme.typography.bodySmall)
            Column(Modifier.selectableGroup()) {
                listOf(
                    stringResource(R.string.sample_controls_radio_opt1),
                    stringResource(R.string.sample_controls_radio_opt2),
                    stringResource(R.string.sample_controls_radio_opt3),
                ).forEachIndexed { index, label ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = radioIndex == index,
                                onClick = { radioIndex = index },
                                role = Role.RadioButton,
                            )
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        RadioButton(selected = radioIndex == index, onClick = null)
                        Text(label, modifier = Modifier.padding(start = 8.dp))
                    }
                }
            }

            Text(text = stringResource(R.string.sample_controls_checkbox_section), style = MaterialTheme.typography.titleSmall)
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Checkbox(checked = checkboxExtra, onCheckedChange = { checkboxExtra = it })
                Text(stringResource(R.string.sample_controls_checkbox_label))
            }

            Text(text = stringResource(R.string.sample_controls_slider_section), style = MaterialTheme.typography.titleSmall)
            Text(
                text = stringResource(R.string.sample_controls_slider_value, (sliderValue * 100).toInt()),
                style = MaterialTheme.typography.bodyMedium,
            )
            Slider(
                value = sliderValue,
                onValueChange = { sliderValue = it },
                valueRange = 0f..1f,
                steps = 9,
                modifier = Modifier.fillMaxWidth(),
            )
            Text(text = stringResource(R.string.sample_controls_slider_hint), style = MaterialTheme.typography.bodySmall)

            Text(text = stringResource(R.string.sample_controls_dialog_section), style = MaterialTheme.typography.titleSmall)
            Button(onClick = { showDialog = true }) {
                Text(stringResource(R.string.sample_controls_dialog_open))
            }
            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text(stringResource(R.string.sample_controls_dialog_title)) },
                    text = { Text(stringResource(R.string.sample_controls_dialog_body)) },
                    confirmButton = {
                        TextButton(onClick = { showDialog = false }) {
                            Text(stringResource(R.string.sample_controls_dialog_ok))
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showDialog = false }) {
                            Text(stringResource(R.string.sample_controls_dialog_cancel))
                        }
                    },
                )
            }
        }
    }
}
