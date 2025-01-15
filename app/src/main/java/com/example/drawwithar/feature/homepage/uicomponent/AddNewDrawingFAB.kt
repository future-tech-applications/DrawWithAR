package com.example.drawwithar.feature.homepage.uicomponent

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.drawwithar.feature.drawingpage.navigation.DrawingPageRoutes
import com.example.drawwithar.util.navigateTo

@Composable
fun AddNewDrawingFAB(
    onClick: () -> Unit = {}
) {
    FloatingActionButton(
        modifier = Modifier
            .size(52.dp)
        ,
        onClick = { onClick() },
        shape = RoundedCornerShape(12.dp),
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = 8.dp,
            pressedElevation = 0.dp
        )
    ) {
        Icon(
            modifier = Modifier
                .size(28.dp)
            ,
            imageVector =  Icons.Default.Add,
            contentDescription = "Add", tint = Color.White
        )
    }
}