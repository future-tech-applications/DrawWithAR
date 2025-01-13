package com.example.drawwithar.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.drawwithar.feature.savedrawingpage.SaveDrawingViewModel


@Composable
fun SaveImagePreviewHolder(
    modifier: Modifier = Modifier,
    viewModel: SaveDrawingViewModel,
) {
    val imageSrc by viewModel.previewImageUri.collectAsState()
    Column(modifier = modifier) {
        Image(
            modifier = Modifier
                .wrapContentSize()
                .clip(RoundedCornerShape(8.dp))
                .border(
                    1.dp,
                    MaterialTheme.colorScheme.surfaceContainer,
                    RoundedCornerShape(8.dp)
                )
            ,
            painter = rememberAsyncImagePainter(imageSrc) ,
            contentDescription = "Save Image Preview",
        )
    }

}
