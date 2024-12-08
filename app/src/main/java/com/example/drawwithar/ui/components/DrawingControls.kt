//package com.example.drawwithar.ui.components
//
//import android.net.Uri
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Button
//import androidx.compose.material3.Slider
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import com.example.drawwithar.EMPTY_IMAGE_URI
//
//@Composable
//fun DrawingControls(
//    modifier: Modifier = Modifier,
//    src: Uri,
//    alpha: Float = 0.5f
//) {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp)
//            .align(Alignment.BottomCenter)
//        ,
//    ) {
//        Slider(
//            value = alpha,
//            onValueChange = { alphaValue = it },
//            valueRange = 0.1f..1.0f,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp)
//        )
//
//        // Button to Enable AR and start Drawing button
//        Button(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(vertical = 16.dp)
//            ,
//            onClick = {
//                imageUri = if(isStartDrawing) EMPTY_IMAGE_URI else imageUri
//                isStartDrawing = !isStartDrawing
//            }
//        ) {
//            val text = if(isStartDrawing ) "Finish" else "Start Drawing"
//            Text(text)
//        }
//    }
//
//}