package com.example.drawwithar.core.common.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.drawwithar.core.common.sharedviewmodel.SharedViewModel
import com.example.drawwithar.feature.drawingpage.templates
import com.example.drawwithar.feature.homepage.MyDrawingsSection

@Composable
fun SectionedContent(sharedViewModel: SharedViewModel, navController: NavHostController, padding: PaddingValues) {
    Column(modifier = Modifier.padding(padding)) {
        MyDrawingsSection(
            sharedViewModel = sharedViewModel,
            title = "My Drawings",
            navController = navController,
            backgroundColor = Color(0xFFFFEBEB)
        )

        Spacer(modifier = Modifier.height(16.dp))


        MyDrawingsSection(
            sharedViewModel = sharedViewModel,
            title = "Templates",
            navController = navController,
            imagesList = templates,
            backgroundColor = Color(0xFFFFE0E0)
        )

        Spacer(modifier = Modifier.height(16.dp))

        MyDrawingsSection(
            sharedViewModel = sharedViewModel,
            title = "Favorites",
            navController = navController,
            backgroundColor = Color(0xFFFFF3CC)
        )
    }
}
