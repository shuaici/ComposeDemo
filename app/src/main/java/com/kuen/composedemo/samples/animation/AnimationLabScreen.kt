package com.kuen.composedemo.samples.animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kuen.composedemo.R
import com.kuen.composedemo.samples.common.SampleScaffold
import com.kuen.composedemo.samples.common.sampleContentPadding

@Composable
fun AnimationLabScreen(onBack: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val fraction by animateFloatAsState(
        targetValue = if (expanded) 1f else 0.35f,
        label = "barFraction",
    )
    var flipped by remember { mutableStateOf(false) }

    SampleScaffold(
        title = stringResource(R.string.sample_animation_title),
        onBack = onBack,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .sampleContentPadding(innerPadding)
                .padding(24.dp),
        ) {
            Text(text = stringResource(R.string.sample_animation_intro), style = MaterialTheme.typography.bodyLarge)

            Text(text = stringResource(R.string.sample_animation_tween_title), style = MaterialTheme.typography.titleSmall)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.CenterStart,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(fraction)
                        .height(40.dp)
                        .padding(horizontal = 8.dp)
                        .background(MaterialTheme.colorScheme.primary),
                )
            }
            Button(onClick = { expanded = !expanded }, modifier = Modifier.padding(top = 8.dp)) {
                Text(stringResource(R.string.sample_animation_toggle_bar))
            }

            Text(
                text = stringResource(R.string.sample_animation_visibility_title),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(top = 24.dp),
            )
            AnimatedVisibility(
                visible = expanded,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically(),
            ) {
                Text(
                    text = stringResource(R.string.sample_animation_visibility_body),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 8.dp),
                )
            }

            Text(
                text = stringResource(R.string.sample_animation_graphics_title),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(top = 24.dp),
            )
            Text(text = stringResource(R.string.sample_animation_graphics_hint), style = MaterialTheme.typography.bodySmall)
            Box(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()
                    .height(72.dp)
                    .graphicsLayer {
                        rotationZ = if (flipped) 180f else 0f
                    }
                    .background(MaterialTheme.colorScheme.tertiaryContainer),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = stringResource(R.string.sample_animation_graphics_label))
            }
            Button(onClick = { flipped = !flipped }) {
                Text(stringResource(R.string.sample_animation_flip))
            }
        }
    }
}
