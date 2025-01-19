package com.example.drawwithar.core.common.ui.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

//@Composable
//fun HomePageSectionItemHolder(
//    modifier: Modifier = Modifier,
//    backgroundColor: Color = MaterialTheme.colorScheme.surface,
//    onClick: () -> Unit = {},
//    content: @Composable () -> Unit = {}
//) {
//
//    Box(
//        modifier = modifier
//            .size(120.dp)
//            .clip(RoundedCornerShape(16.dp))
//            .background(backgroundColor)
//            .clickable(
//                onClick = {
//                    onClick()
//                }
//            )
//        ,
//        contentAlignment = Alignment.Center,
//    ) {
//        content()
//    }
//}


@Composable
fun HomePageSectionItemHolder(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    isFavorited: Boolean = false,
    itemSrc: Any?, // Src of the image
    onClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {}, // Passes the URI of the item as String
    content: @Composable () -> Unit = {}
) {
    Box(
        modifier = modifier
            .size(120.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .clickable(onClick = { onClick() }),
        contentAlignment = Alignment.Center,
    ) {
        // Main content of the item
        content()

        // Favorite icon in the top-right corner
        if(itemSrc != null && itemSrc is Uri) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp) // Padding to keep icon within bounds
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(2.dp) // Padding to make space for icon to reside in
                    .clickable { onFavoriteClick() }, // Click action for the favorite icon
            ) {
                Icon(
                    imageVector = if (isFavorited) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = if (isFavorited) "Unfavorite" else "Favorite",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

    }
}

