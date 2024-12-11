package com.example.drawwithar.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.drawwithar.R
import com.example.drawwithar.model.OpacitySliderModel

@Composable
fun OpacitySliderControl(
    modifier: Modifier = Modifier,
    opacitySliderModel: OpacitySliderModel,
    isSliderVisible: Boolean = false
) {

    Column(modifier = modifier) {
        if(isSliderVisible) {
            OpacitySlider(
                modifier = modifier,
                opacitySliderModel = opacitySliderModel
            )
        }

        // => Button to Enable AR and start Drawing button
        if(!opacitySliderModel.isStartDrawing) {
            Button(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(vertical = 4.dp)
                    .align(Alignment.CenterHorizontally)
                ,
                onClick = {
                    opacitySliderModel.onStartDrawing()
                }
            ) {
                val text = "All Set? Start Drawing"
                Text(text)
            }
        }
    }

}