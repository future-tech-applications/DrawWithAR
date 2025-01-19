package com.example.drawwithar.feature.homepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.drawwithar.R
import com.example.drawwithar.core.common.ui.components.CustomTopAppBar
import com.example.drawwithar.core.common.ui.components.SectionedContent


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
                        painter = painterResource(id = R.drawable.draw_with_ar_logo),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .padding(top = 4.dp, bottom = 4.dp, start = 8.dp, end = 12.dp)
                            .size(40.dp)
                            .border(
                                1.dp,
                                MaterialTheme.colorScheme.background,
                                RoundedCornerShape(10.dp)
                            )
                    )
                }
            )
        },
    ) { padding ->
        SectionedContent(navController, padding, viewModel)
    }
}





