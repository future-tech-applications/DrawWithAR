package com.example.drawwithar.feature.homepage.uicomponent

import android.net.Uri
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.drawwithar.core.common.sharedviewmodel.getSharedViewModel
import com.example.drawwithar.core.common.ui.components.ColorConstants
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
    navController: NavHostController,
    title: String = "",
    backgroundColor: Color = ColorConstants.HOME_SECTION_BACKGROUND,
    imagesList: List<Any> = emptyList()
) {
    val sharedViewModel = getSharedViewModel()
    val viewModel =  hiltViewModel<HomeViewModel>()
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
            if (title == HomeSections.MyDrawings.title && imagesList.isEmpty()) {
                SquareAddButton(
                    modifier = Modifier.align(Alignment.Center),
                    onClick = {
                        navController.navigate(DrawingPageRoutes.DrawingPage.route)
                    }
                )
            }
            if (title == HomeSections.Favorites.title && imagesList.isEmpty()) {
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
                if (title == HomeSections.MyDrawings.title && imagesList.isNotEmpty()) {
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
                val limitedList = imagesList.take(8)
                items(limitedList.size) { index ->
                    val imageItem = imagesList[index]
                    val imageSrc = if (imageItem is Uri) {
                        rememberAsyncImagePainter(model = imageItem as Uri)
                    } else {
                        painterResource(id = imageItem as Int)
                    }
                    HomePageSectionItemHolder(
                        onClick = {
                            if (title == HomeSections.MyDrawings.title) {
                                ImageUtils.openImageInGallery(navController.context, imageItem as Uri)
                            } else {
                                // set selected image uri and navigate to drawing page
                                sharedViewModel.selectImageForDrawing(imageSrc)
                                navController.navigate(DrawingPageRoutes.DrawingPage.route)
                            }
                        },
                        itemSrc = imageItem,
                        onFavoriteClick = {
                            // add to favorite
                            viewModel.insertDrawing(
                                DrawingEntity(
                                    uri = (imageItem as Uri).toString(),
                                    isFavorite = true
                                )
                            )
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