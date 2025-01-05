package com.example.drawwithar.feature.homepage.bottomnavigation

import com.example.drawwithar.R

data class BottomNavigationItem(

   val item: DrawableItemModel? = null,
   val route: String = "",
)




/**
 * Get list of bottom navigation items
 *
 * @return
 */
fun getListOfBottomNavigationItems(): List<BottomNavigationItem> {
   return listOf(
      BottomNavigationItem(
         item = DrawableItemModel(
            id = 1,
            name = "Dashboard",
            resId = R.drawable.baseline_opacity_24,
         ),
         route = "dashboard"
      ),
      BottomNavigationItem(
         item = DrawableItemModel(
            id = 2,
            name = "Calendar",
            resId = R.drawable.baseline_lock_24,
         ),
         route = "nav_calendar"
      ),
      BottomNavigationItem(
         item = DrawableItemModel(
            id = 3,
            name = "To Do",
            resId = R.drawable.baseline_flash_on_24,
         ),
         route = "nav_todo"
      ),
   )
}



