package com.example.drawwithar.core.common.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.drawwithar.feature.drawingpage.templates
import com.example.drawwithar.feature.homepage.HomeViewModel
import com.example.drawwithar.feature.homepage.uicomponent.HomePageDrawingsSection

@Composable
fun SectionedContent(
    navController: NavHostController,
    padding: PaddingValues,
    viewModel: HomeViewModel
) {
    val savedDrawingsList by viewModel.savedDrawingsList.collectAsState(initial = emptyList())
    //val favoriteDrawingsList by viewModel.favoriteDrawingsList.collectAsState(initial = emptyList())
    Column(
        modifier = Modifier
            .padding(padding)
            .verticalScroll(rememberScrollState())
    ) {
        HomePageDrawingsSection(
            viewModel = viewModel,
            title = HomeSections.MyDrawings.title,
            navController = navController,
            imagesList =  savedDrawingsList.reversed(), // show latest first

        )
        Spacer(modifier = Modifier.height(16.dp))
        HomePageDrawingsSection(
            viewModel = viewModel,
            title = HomeSections.Favorites.title,
            navController = navController,
        )
        Spacer(modifier = Modifier.height(16.dp))
        HomePageDrawingsSection(
            viewModel = viewModel,
            title = HomeSections.Templates.title,
            navController = navController,
            imagesList = templates,
        )
    }
}

sealed class HomeSections(val title: String) {
    data object MyDrawings : HomeSections("My Drawings")
    data object Favorites : HomeSections("Favorites")
    data object Templates : HomeSections("Templates")
}

