package com.example.drawwithar.core.common.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.drawwithar.core.common.sharedviewmodel.SharedViewModel
import com.example.drawwithar.core.common.sharedviewmodel.getSharedViewModel
import com.example.drawwithar.feature.drawingpage.navigation.DrawingPageRoutes
import com.example.drawwithar.util.navigateTo

@Composable
fun SectionItemImageHolder(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    image: Painter,
    backgroundColor: Color = MaterialTheme.colorScheme.surface
) {
    val sharedViewModel = getSharedViewModel()
    Box(
        modifier = modifier
            .size(120.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .clickable(
                onClick = {
                    sharedViewModel.selectImage(image)
                    navController.navigateTo(DrawingPageRoutes.DrawingPage.route)
                }
            )
        ,
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}
