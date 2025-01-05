package com.example.drawwithar.core.common.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.drawwithar.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    title: String,
    isTransparent: Boolean = false,
    onBackPressed: () -> Unit = {},
    onMenuPressed: () -> Unit = {},
    isShowBackBtn: Boolean = true, // Show/hide back button
    isShowMenuBtn: Boolean = false, // Show/hide menu button
    menuComposable: (@Composable () -> Unit)? = null, // Optional custom menu button
    navigationComposable: (@Composable () -> Unit)? = null, // Optional custom navigation composable
    actions: @Composable RowScope.() -> Unit = {}, // Action buttons
) {
    val containerColor = if (isTransparent){
        Color.Black.copy(alpha = 0.2f)
    }
    else {
        MaterialTheme.colorScheme.primary
    }
    TopAppBar(
        colors = topAppBarColors(
            containerColor = containerColor,
            scrolledContainerColor = Color.Transparent,
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White
        ),
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterStart // Center-align the title
            ) {
                Text(
                    text = title,
                    fontFamily = MaterialTheme.typography.labelMedium.fontFamily,
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 18.sp,
                        color = Color.White // Title text color
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        },
        navigationIcon = {
            Row {
                if (isShowBackBtn) {
                    if (navigationComposable != null) {
                        navigationComposable()
                    } else {
                        IconButton(onClick = onBackPressed) {
                            Icon(
                                modifier = Modifier.size(18.dp),
                                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_new_24),
                                tint = Color.White,
                                contentDescription = "Back"
                            )
                        }
                    }
                }
                if (isShowMenuBtn) {
                    if (menuComposable != null) {
                        menuComposable()
                    } else {
                        IconButton(onClick = onMenuPressed) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                                tint = Color.White,
                                contentDescription = "Menu"
                            )
                        }
                    }
                }
            }
        },
        actions = actions
    )
}




//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun CustomTopAppBar(
//    title:String, onBackPresses: () -> Unit,
//    isShowBackBtn:Boolean = true,
//    isShowMenuBtn:Boolean = false,
//    actions: @Composable RowScope.() -> Unit = {},
//    ) {
//    TopAppBar(
//        colors = topAppBarColors(
//            containerColor = MaterialTheme.colorScheme.primary,
//            titleContentColor = MaterialTheme.colorScheme.onPrimary,
//        ),
//        title = {Row {
//            Text(
//                text = title,
//                fontFamily = MaterialTheme.typography.labelMedium.fontFamily,
//                fontSize = MaterialTheme.typography.titleMedium.fontSize,
//                color = MaterialTheme.colorScheme.onPrimary,
//
//            )
//        }},
//        navigationIcon = {
//            if(isShowBackBtn) {
//                IconButton(onClick = { onBackPresses() }) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.baseline_arrow_back_ios_new_24),
//                        tint = MaterialTheme.colorScheme.onPrimary,
//                        contentDescription = "Back"
//                    )
//                }
//            }
//        },
//        actions = actions,
//    )
//}