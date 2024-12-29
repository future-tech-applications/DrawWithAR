package com.example.drawwithar.feature.drawingpage.uicomponent

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.drawwithar.R
import com.example.drawwithar.core.common.Const
import com.example.drawwithar.feature.drawingpage.model.OpacitySliderModel

@Composable
fun OpacitySlider(
    modifier: Modifier = Modifier,
    opacitySliderModel: OpacitySliderModel,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp, horizontal = 4.dp)
            .border(1.dp, Color.White, RoundedCornerShape(16.dp))
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.padding(horizontal = 4.dp),
            painter = painterResource(id = R.drawable.baseline_opacity_24),
            tint = Color.Gray,
            contentDescription = "Opacity Low"
        )
        //Spacer(modifier = Modifier.weight(1f))
        Slider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            value = opacitySliderModel.alpha,
            onValueChange = { opacitySliderModel.onAlphaChange(it) },
            valueRange = Const.OpacitySlider.MIN_VALUE..Const.OpacitySlider.MAX_VALUE,

            )
        Icon(
            modifier = Modifier.padding(horizontal = 4.dp),
            painter = painterResource(id = R.drawable.baseline_opacity_24),
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = "Opacity High"
        )

    }

}