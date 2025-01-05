package com.example.drawwithar.feature.homepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import com.example.drawwithar.R
import com.example.drawwithar.core.camera.CameraCapture
import com.example.drawwithar.core.common.ui.components.CustomTopAppBar
import com.example.drawwithar.core.common.ui.components.SectionedContent
import com.example.drawwithar.core.common.ui.components.SquareAddButton
import com.example.drawwithar.core.common.ui.components.SquareImageHolder
import com.example.drawwithar.feature.drawingpage.navigation.DrawingPageRoutes
import com.example.drawwithar.feature.homepage.navigation.HomePageRoutes
import com.example.drawwithar.util.navigateTo

@Composable
fun HomeScreen(
    navController: NavHostController
) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Draw with AR",
                isShowBackBtn = false,
                isShowMenuBtn = true,
                menuComposable = {

                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_round_hd),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .padding(vertical = 4.dp, horizontal = 8.dp)
                            .size(42.dp)
                            .border(
                                1.dp,
                                MaterialTheme.colorScheme.background,
                                CircleShape

                            )
                    )
                }
            )
        },
        bottomBar = {  },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigateTo(DrawingPageRoutes.DrawingPage.route) },
                shape = CircleShape,
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.White)
            }
        },
    ) { padding ->
        SectionedContent(navController, padding)
    }
}




@Composable
fun MyDrawingsSection(
    navController: NavHostController,
    title: String = "",
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    imagesList: List<Any> = emptyList()
) {
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
                    navController.navigateTo(HomePageRoutes.SeeAllPage.route + title)
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
            if (title == "My Drawings" && imagesList.isEmpty()) {
                SquareAddButton(
                    modifier = Modifier.align(Alignment.Center),
                    onClick = { navController.navigateTo(DrawingPageRoutes.DrawingPage.route) }
                )
            }

            if (title == "Favorites" && imagesList.isEmpty()) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "Nothing to Show",
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    fontSize = MaterialTheme.typography.titleSmall.fontSize
                )
            }
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(imagesList.size) { index ->
                    val image = imagesList[index]
                    SquareImageHolder(
                        image = painterResource(id = image as Int),
                    )
                }
            }
        }
    }


    @Composable
    fun TemplatesSection() {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Templates", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    "See all",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable { /* Action */ }
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(
                        MaterialTheme.colorScheme.surfaceContainer,
                        RoundedCornerShape(8.dp)
                    )
            ) {
                // Placeholder for templates
            }
        }
    }

    @Composable
    fun FavoritesSection() {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Favorites", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    "See all",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable { /* Action */ }
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(
                        MaterialTheme.colorScheme.surfaceContainer,
                        RoundedCornerShape(8.dp)
                    )
            ) {
                // Placeholder for templates
            }
        }
    }
}


