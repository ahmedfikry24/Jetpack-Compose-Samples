package com.example.jetpackcomposesamples.ui.get_permission


import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.READ_MEDIA_IMAGES
import android.Manifest.permission.READ_MEDIA_VIDEO
import android.Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext


@Composable
fun GetPermission() {
    val context = LocalContext.current as Activity
    var showRationaleDialog by remember { mutableStateOf(false) }
    var showGoSettingsDialog by remember { mutableStateOf(false) }
    val photoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { image ->
        image?.let { Log.e("photoPicker", it.toString()) }
    }
    val requestPermissions = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) {
        if (it["android.permission.READ_MEDIA_IMAGES"] == true)
            photoPicker.launch("image/*")
        else if (it["android.permission.READ_MEDIA_IMAGES"] == false && !showRationaleDialog)
            showRationaleDialog = true
        else showGoSettingsDialog = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White.copy(alpha = .8f)),
        verticalArrangement = Arrangement.Center
    ) {
        TextButton(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                requestPermission(requestPermissions)
            }
        ) {
            Text(text = "get Image")
        }
    }
    if (showRationaleDialog)
        GetPermissionDialog(
            isRationale = true,
            onDismiss = { showRationaleDialog = false },
            onClick = { requestPermission(requestPermissions) }
        )

    if (showGoSettingsDialog) {
        showRationaleDialog = false
        GetPermissionDialog(
            isRationale = false,
            onDismiss = { showGoSettingsDialog = false },
            onClick = { context.goToSettings() }
        )
    }
}

fun requestPermission(requestPermissions: ManagedActivityResultLauncher<Array<String>, Map<String, @JvmSuppressWildcards Boolean>>) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
        requestPermissions.launch(
            arrayOf(
                READ_MEDIA_IMAGES,
                READ_MEDIA_VIDEO,
                READ_MEDIA_VISUAL_USER_SELECTED
            )
        )
    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        requestPermissions.launch(arrayOf(READ_MEDIA_IMAGES, READ_MEDIA_VIDEO))
    } else {
        requestPermissions.launch(arrayOf(READ_EXTERNAL_STORAGE))
    }
}

fun Activity.goToSettings() {
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", packageName, null)
    ).also { startActivity(it) }
}
