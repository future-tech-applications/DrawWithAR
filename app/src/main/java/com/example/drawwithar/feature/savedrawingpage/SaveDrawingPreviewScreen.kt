package com.example.drawwithar.feature.savedrawingpage

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.example.drawwithar.R
import com.example.drawwithar.core.common.ui.components.BorderedButton
import com.example.drawwithar.feature.homepage.navigation.HomePageRoutes
import com.example.drawwithar.feature.savedrawingpage.uicomponents.SaveDrawingPreviewButtons
import com.example.drawwithar.ui.components.RetakePictureButton
import com.example.drawwithar.ui.components.SaveImagePreviewHolder
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalPermissionsApi::class, ExperimentalCoroutinesApi::class)
@Composable
fun SaveDrawingPreviewScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: SaveDrawingViewModel,
    onRetakeClick: () -> Unit = {},
) {

    val saveResult by viewModel.saveResult.collectAsState()
    LaunchedEffect(saveResult) {
        saveResult?.let {
            if (it.isSuccess) {
                Toast.makeText(navController.context, "Drawing saved successfully!", Toast.LENGTH_SHORT).show()
                navController.navigate(
                    HomePageRoutes.HomePage.route,
                    navOptions = NavOptions.Builder()
                        .setPopUpTo(HomePageRoutes.HomePage.route, true)
                        .build()
                )
            } else {
                Toast.makeText(navController.context, "Failed to save Drawing: ${it.exceptionOrNull()}", Toast.LENGTH_LONG).show()
            }
        }
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(vertical = 16.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        SaveImagePreviewHolder(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally),
            viewModel = viewModel,
        )
        SaveDrawingPreviewButtons(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .align(Alignment.CenterHorizontally),
            navController = navController,
            onRetakeClick = {
                onRetakeClick()
            },
            onSaveClick = {
                viewModel.saveImageToGallery(navController.context)
            }
        )


        if (viewModel.isSaving.collectAsState().value) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }

}


