package com.example.drawwithar.feature.savedrawingpage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.drawwithar.ui.components.RetakePictureButton
import com.example.drawwithar.ui.components.SaveImagePreviewHolder
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalPermissionsApi::class, ExperimentalCoroutinesApi::class)
@Composable
fun SaveDrawingReviewScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: SaveDrawingViewModel,
    onRetakeClick: () -> Unit = {},
) {

    Box(modifier = Modifier
        .fillMaxSize()
    ) {
        SaveImagePreviewHolder(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopCenter),
            viewModel = viewModel,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.BottomCenter),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BorderedButton(
                text = "Cancel",
                onClick = {
                    navController.popBackStack()
                }
            )

            RetakePictureButton(
                modifier = Modifier
                    .size(64.dp)
                    ,
                onClick = {
                    onRetakeClick()
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.retake_icon),
                    contentDescription = "Retake Image"
                )
            }
            BorderedButton(text = "Save", onClick = {})
        }
    }
}


