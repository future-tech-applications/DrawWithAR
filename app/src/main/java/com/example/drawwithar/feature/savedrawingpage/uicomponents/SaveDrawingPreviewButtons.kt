package com.example.drawwithar.feature.savedrawingpage.uicomponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.drawwithar.R
import com.example.drawwithar.core.common.ui.components.BorderedButton
import com.example.drawwithar.feature.savedrawingpage.SaveDrawingViewModel
import com.example.drawwithar.ui.components.RetakePictureButton

@Composable
fun SaveDrawingPreviewButtons (
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onRetakeClick: () -> Unit = {},
    onSaveClick: () -> Unit = {},
    onCancelClick: () -> Unit = {},
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BorderedButton(
            text = "Cancel",
            onClick = { onCancelClick() }
        )
        RetakePictureButton(
            modifier = Modifier
                .size(64.dp),
            onClick = { onRetakeClick() }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.retake_icon),
                contentDescription = "Retake Image"
            )
        }
        BorderedButton(
            text = "Save",
            onClick = { onSaveClick() }
        )
    }
}