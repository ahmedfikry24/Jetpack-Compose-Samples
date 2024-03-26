package com.example.jetpackcomposesamples.ui.get_permission

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GetPermissionDialog(
    isRationale: Boolean,
    onDismiss: () -> Unit,
    onClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
    ) {
        Column(modifier = Modifier.background(Color.White)) {
            Text(text = "we need an access to your storage for help you to upload your images, please give us the permission")
            TextButton(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp),
                onClick = onClick
            ) {
                Text(text = if (isRationale) "ok" else "go to settings")
            }
        }
    }
}