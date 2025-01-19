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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.example.drawwithar.R
import com.example.drawwithar.core.common.ui.components.BorderedButton
import com.example.drawwithar.core.common.ui.components.ColorConstants
import com.example.drawwithar.core.common.ui.components.RichConfirmDialog
import com.example.drawwithar.feature.homepage.navigation.HomePageRoutes
import com.example.drawwithar.feature.savedrawingpage.navigation.SaveDrawingPageRoutes
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

    // on finish drawing dialog state
    val isSaveDrawingDialogOpened = rememberSaveable { mutableStateOf(false) }

    val saveResult by viewModel.saveResult.collectAsState()
    LaunchedEffect(saveResult) {
        saveResult?.let {
            if (it.isSuccess) {
                isSaveDrawingDialogOpened.value = true
                //Toast.makeText(navController.context, "Drawing saved successfully!", Toast.LENGTH_SHORT).show()

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
            ,
            onCancelClick = {
                navController.popBackStack()
            }
        )

        // on finish drawing dialog
        if(isSaveDrawingDialogOpened.value) {
            RichConfirmDialog(
                showDialog = isSaveDrawingDialogOpened.value,
                imageSrc = R.drawable.draw_with_ar_logo,
                dismissText = "Leave me here, am still drawing",
                confirmText = "Take me HOME",
                dialogColor = ColorConstants.RICH_CONFIRM_DIALOG_BACKGROUND_SUCCESS,
                onDismiss = {
                    isSaveDrawingDialogOpened.value = false
                    navController.popBackStack()

                },
                onConfirm = {
                    navController.navigate(
                        HomePageRoutes.HomePage.route,
                        navOptions = NavOptions.Builder()
                            .setPopUpTo(HomePageRoutes.HomePage.route, true)
                            .build()
                    )
                },
                title = "Congrats! Your beautiful drawing is now saved in 'My Drawings' "
            )
        }


        if (viewModel.isSaving.collectAsState().value) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }

}


