package com.example.drawwithar.feature.homepage.bottomnavigation

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class DrawableItemModel(
   val id: Int = 0,
   @DrawableRes
   val resId: Int = 0,
   @ColorRes
   val drawableTint: Color = Color.Black,
   val name: String = ""
)
