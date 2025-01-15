package com.example.drawwithar.core.common.ui.components

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.drawwithar.feature.drawingpage.templates
import com.example.drawwithar.feature.homepage.HomePageDrawingsSection
import com.example.drawwithar.util.MediaStoreUtil.fetchSavedImages

@Composable
fun SectionedContent(navController: NavHostController, padding: PaddingValues) {
    var savedDrawingsList by remember { mutableStateOf<List<Uri>>(emptyList()) }
    LaunchedEffect(Unit) {
        savedDrawingsList = fetchSavedImages(navController.context)
    }

    Column(modifier = Modifier.padding(padding)) {
        HomePageDrawingsSection(
            title = "My Drawings",
            navController = navController,
            imagesList = savedDrawingsList
        )
        Spacer(modifier = Modifier.height(16.dp))
        HomePageDrawingsSection(
            title = "Favorites",
            navController = navController,
        )
        Spacer(modifier = Modifier.height(16.dp))
        HomePageDrawingsSection(
            title = "Templates",
            navController = navController,
            imagesList = templates,
        )
    }
}
