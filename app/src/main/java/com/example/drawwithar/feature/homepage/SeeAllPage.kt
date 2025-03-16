package com.example.drawwithar.feature.homepage

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.drawwithar.core.common.sharedviewmodel.getSharedViewModel
import com.example.drawwithar.core.common.ui.components.CustomTopAppBar
import com.example.drawwithar.core.common.ui.components.HomePageSectionItemHolder
import com.example.drawwithar.core.common.ui.components.HomeSections
import com.example.drawwithar.feature.drawingpage.navigation.DrawingPageRoutes
import com.example.drawwithar.room.DrawingEntity
import com.example.drawwithar.util.ImageUtils

@Composable
fun SeeAllPage(
    viewModel: HomeViewModel,
    navController: NavHostController,
    title: String,
    imagesList: List<Any>,
    backgroundColor: Color = MaterialTheme.colorScheme.background
) {
    val context = LocalContext.current
    val favoriteDrawingUris by viewModel.favoriteDrawingsList.collectAsState()
    val sharedViewModel = getSharedViewModel()
    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = title,
                isShowBackBtn = true,
                onBackPressed = {
                    navController.popBackStack()
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(paddingValues)
        ) {
            if (imagesList.isEmpty()) {
                Text(
                    text = "No items to display",
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize
                )
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(imagesList.size) { index ->
                        val imageItem = imagesList[index]
                        val image = if (imageItem is Uri) {
                            rememberAsyncImagePainter(model = imageItem as Uri)
                        } else {
                            painterResource(id = imageItem as Int)
                        }
                        HomePageSectionItemHolder(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f)
                                .clip(RoundedCornerShape(12.dp))
                                .background(MaterialTheme.colorScheme.surface),
                            isFavorited = if(imageItem is Uri) favoriteDrawingUris.contains(imageItem) else false,
                            onClick = {
                                if (title == HomeSections.MyDrawings.title) {
                                    ImageUtils.openImageInGallery(navController.context, imageItem as Uri)
                                } else {
                                    // set selected image uri and navigate to drawing page
                                    sharedViewModel.selectImageForDrawing(image)
                                    navController.navigate(DrawingPageRoutes.DrawingPage.route)
                                }
                            },
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
                            },
                            itemSrc = imageItem
                        ) {
                            Image(
                                painter = image,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxSize()
                            )
                        }
                    }
                    }
                }
            }
        }
    }

