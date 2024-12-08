package com.example.drawwithar.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun CustomTopAppBar(title:String, onBackPresses: () -> Unit, isShowBackBtn:Boolean = true,
//                    actions: @Composable RowScope.() -> Unit = {}, isShowMenuBtn:Boolean = false ) {
//    TopAppBar(
//        colors = topAppBarColors(
//            containerColor = MaterialTheme.colorScheme.primary,
//            titleContentColor = MaterialTheme.colorScheme.onPrimary,
//        ),
//        title = {
//            Row {
//                Text(title)
//            }
//        },
//        navigationIcon = {
//            if(isShowBackBtn) {
//                IconButton(onClick = { onBackPresses() }) {
//                    Icon(
//                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
//                        tint = MaterialTheme.colorScheme.onPrimary,
//                        contentDescription = "Back"
//                    )
//                }
//            }
//            if(isShowMenuBtn){
//                IconButton(onClick = { onBackPresses() }) {
//                    Icon(
//                        imageVector = Icons.Rounded.Menu,
//                        tint = MaterialTheme.colorScheme.onPrimary,
//                        contentDescription = "Navigation Drawer"
//                    )
//                }
//            }
//        },
//        actions = actions
//    )
//}

@Composable
fun CustomTopAppBar(
    title:String, onBackPresses: () -> Unit,
    isShowBackBtn:Boolean = true,
    isShowMenuBtn:Boolean = false,
    actions: @Composable RowScope.() -> Unit = {},
    ) {
    TopAppBar(
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
        ),
        title = {Row {
            Text(title)
        }},
        navigationIcon = {
            if(isShowBackBtn) {
                IconButton(onClick = { onBackPresses() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        contentDescription = "Back"
                    )
                }
            }
        },
        actions = actions,
    )
}