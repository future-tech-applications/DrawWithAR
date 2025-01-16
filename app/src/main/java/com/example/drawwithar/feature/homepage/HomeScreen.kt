package com.example.drawwithar.feature.homepage

import android.net.Uri
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
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.drawwithar.R
import com.example.drawwithar.core.common.sharedviewmodel.getSharedViewModel
import com.example.drawwithar.core.common.ui.components.ColorConstants
import com.example.drawwithar.core.common.ui.components.CustomTopAppBar
import com.example.drawwithar.core.common.ui.components.SectionedContent
import com.example.drawwithar.core.common.ui.components.SquareAddButton
import com.example.drawwithar.core.common.ui.components.HomePageSectionItemHolder
import com.example.drawwithar.feature.drawingpage.navigation.DrawingPageRoutes
import com.example.drawwithar.feature.homepage.navigation.HomePageRoutes
import com.example.drawwithar.util.navigateTo

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel
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
    ) { padding ->
        SectionedContent(navController, padding, viewModel)
    }
}





