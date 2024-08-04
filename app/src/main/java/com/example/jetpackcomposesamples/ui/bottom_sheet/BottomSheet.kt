package com.example.jetpackcomposesamples.ui.bottom_sheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposesamples.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet() {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextButton(
            colors = ButtonDefaults.textButtonColors(containerColor = Color.Blue),
            onClick = { scope.launch { sheetState.show() } }
        ) {
            Text(
                text = stringResource(R.string.open_bottomsheet),
                fontSize = 14.sp,
                color = Color.White
            )
        }
    }
    if (sheetState.isVisible)
        ModalBottomSheet(
            modifier = Modifier.fillMaxWidth(),
            sheetState = sheetState,
            onDismissRequest = { scope.launch { sheetState.hide() } }
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.hello_bottomsheet),
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = stringResource(R.string.hello_bottomsheet),
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Text(
                    text = stringResource(R.string.hello_bottomsheet),
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
        }
}
