package com.example.drawwithar.feature.drawingpage.uicomponent

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.drawwithar.feature.drawingpage.model.OpacitySliderModel

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

    }

}