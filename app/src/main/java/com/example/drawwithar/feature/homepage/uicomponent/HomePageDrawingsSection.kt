package com.example.drawwithar.feature.homepage.uicomponent

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.drawwithar.core.common.model.CustomToastData
import com.example.drawwithar.core.common.sharedviewmodel.getSharedViewModel
import com.example.drawwithar.core.common.ui.components.ColorConstants
import com.example.drawwithar.core.common.ui.components.CustomToast
import com.example.drawwithar.core.common.ui.components.HomePageSectionItemHolder
import com.example.drawwithar.core.common.ui.components.HomeSections
import com.example.drawwithar.core.common.ui.components.SquareAddButton
import com.example.drawwithar.feature.drawingpage.navigation.DrawingPageRoutes
import com.example.drawwithar.feature.homepage.HomeViewModel
import com.example.drawwithar.feature.homepage.navigation.HomePageRoutes
import com.example.drawwithar.room.DrawingEntity
import com.example.drawwithar.util.ImageUtils

@Composable
fun HomePageDrawingsSection(
    viewModel: HomeViewModel,
    navController: NavHostController,
    title: String = "",
    backgroundColor: Color = ColorConstants.HOME_SECTION_BACKGROUND,
    imagesList: List<Any> = emptyList()
) {
    val context = LocalContext.current
    var imagesListToDisplay: List<Any> = imagesList
    val favoriteDrawingUris by viewModel.favoriteDrawingsList.collectAsState()

    if (title == HomeSections.Favorites.title && favoriteDrawingUris.isNotEmpty()) imagesListToDisplay = favoriteDrawingUris
    val sharedViewModel = getSharedViewModel()
    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = title, fontSize = MaterialTheme.typography.titleMedium.fontSize,)
            Spacer(modifier = Modifier.weight(1f))
            Text(
                "See all",
                fontSize = MaterialTheme.typography.titleSmall.fontSize,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable {
                    navController.navigate(HomePageRoutes.SeeAllPage.route + title)
                }
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(backgroundColor, RoundedCornerShape(8.dp))
        ) {
            if (title == HomeSections.MyDrawings.title && imagesListToDisplay.isEmpty()) {
                SquareAddButton(
                    modifier = Modifier.align(Alignment.Center),
                    onClick = {
                        navController.navigate(DrawingPageRoutes.DrawingPage.route)
                    }
                )
            }
            if (title == HomeSections.Favorites.title && favoriteDrawingUris.isEmpty()) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "No items to display",
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                    fontSize = MaterialTheme.typography.titleSmall.fontSize
                )
            }
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                // first static item to be displayed
                if (title == HomeSections.MyDrawings.title && imagesListToDisplay.isNotEmpty()) {
                    item {
                        HomePageSectionItemHolder(
                            onClick = {
                                navController.navigate(DrawingPageRoutes.DrawingPage.route)
                            },
                            itemSrc = null
                        ) {
                            SquareAddButton(
                                modifier = Modifier
                                    .fillMaxSize(0.4f)
                                    .align(Alignment.Center)
                            ) {
                                navController.navigate(DrawingPageRoutes.DrawingPage.route)
                            }
                        }
                    }
                }

                // list of items
                // show only 8 items
                val limitedList = imagesListToDisplay.take(8)
                items(limitedList.size) { index ->
                    val isOpensInGallery = title == HomeSections.MyDrawings.title ||
                            title == HomeSections.Favorites.title

                    val imageItem = imagesListToDisplay[index]
                    val imageSrc = if (imageItem is Uri) {
                        rememberAsyncImagePainter(model = imageItem)
                    } else {
                        painterResource(id = imageItem as Int)
                    }
                    HomePageSectionItemHolder(
                        onClick = {
                            if (isOpensInGallery) {
                                ImageUtils.openImageInGallery(navController.context, imageItem as Uri)
                            } else {
                                // set selected image uri and navigate to drawing page
                                sharedViewModel.selectImageForDrawing(imageSrc)
                                navController.navigate(DrawingPageRoutes.DrawingPage.route)
                            }
                        },
                        itemSrc = imageItem,
                        isFavorited = isOpensInGallery && favoriteDrawingUris.contains(imageItem as Uri),
                        onFavoriteClick = { src, isFavorite, isFromFavorite ->
                            Log.d("onFavClick", "$src isFavorite: $isFavorite")
                            val drawingEntity = DrawingEntity(
                                uri = (src as Uri).toString(),
                                isFavorite = isFavorite

                            )
                            // add to favorite
                            if(isFavorite && !isFromFavorite) {
                                viewModel.insertDrawing(drawingEntity)
                                Toast.makeText(context, "Added to Favorites", Toast.LENGTH_SHORT).show()
                            }
                            else {
                                viewModel.deleteDrawing(drawingEntity)
                                Toast.makeText(context, "Removed from Favorites", Toast.LENGTH_SHORT).show()

                            }
                        }
                    ) {
                        Image(
                            painter = imageSrc,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }
    }


}