package com.example.jetpackcomposesamples.ui.container_transform_animation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ContainerTransformAnimation() {
    val configuration = LocalConfiguration.current
    val textHeight = (configuration.screenHeightDp / 4).dp
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        LazyColumn(Modifier.fillMaxSize()) {
            items(4) {
                Row(
                    modifier = Modifier.fillParentMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.height(textHeight),
                        text = "Ahmed Fikry",
                        fontSize = 18.sp
                    )
                }
            }
        }
        var fabState by remember { mutableStateOf(FabStatus.FAB_ICON) }
        val transition = updateTransition(targetState = fabState, label = null)
        val height by transition.animateDp(label = "height") {
            when (it) {
                FabStatus.FAB_ICON -> 58.dp
                FabStatus.ADD_CONTENT -> configuration.screenHeightDp.dp
            }
        }
        val width by transition.animateDp(label = "width") {
            when (it) {
                FabStatus.FAB_ICON -> 58.dp
                FabStatus.ADD_CONTENT -> configuration.screenWidthDp.dp
            }
        }
        val padding by transition.animateDp(label = "padding") {
            when (it) {
                FabStatus.FAB_ICON -> 16.dp
                FabStatus.ADD_CONTENT -> 0.dp
            }
        }
        val background by transition.animateColor(label = "background") {
            when (it) {
                FabStatus.FAB_ICON -> MaterialTheme.colorScheme.primary
                FabStatus.ADD_CONTENT -> MaterialTheme.colorScheme.background
            }
        }
        transition.AnimatedContent(
            modifier = Modifier
                .size(
                    width = width,
                    height = height
                )
                .padding(bottom = padding, end = padding)
                .background(background)
        ) {
            when (it) {
                FabStatus.FAB_ICON -> {
                    FloatingActionButton(
                        modifier = Modifier.fillMaxSize(),
                        containerColor = MaterialTheme.colorScheme.primary,
                        onClick = { fabState = FabStatus.ADD_CONTENT }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Add,
                            contentDescription = null,
                            tint = Color(android.graphics.Color.WHITE)
                        )
                    }
                }

                FabStatus.ADD_CONTENT -> ContentAfterAnimation { fabState = FabStatus.FAB_ICON }
            }
        }

    }
}