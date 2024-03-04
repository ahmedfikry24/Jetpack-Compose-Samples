package com.example.jetpackcomposesamples.ui.container_transform_animation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ContentAfterAnimation(
    onClick: () -> Unit
) {
    Column(Modifier.fillMaxSize()) {
        FilledIconButton(
            onClick = onClick
        ) {
            Icon(
                imageVector = Icons.Outlined.ArrowBack,
                contentDescription = null,
                tint = Color(android.graphics.Color.BLACK)
            )
        }
    }

}