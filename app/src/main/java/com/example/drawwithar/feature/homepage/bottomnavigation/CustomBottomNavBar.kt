package com.example.drawwithar.feature.homepage.bottomnavigation

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

/**
* function to show bottom bar
*
* @param navController
* @param modifier
*/
@Composable
fun CustomBottomNavBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    bottomNavigationItems: List<BottomNavigationItem>
) {


   NavigationBar(
       modifier = modifier,
       containerColor = MaterialTheme.colorScheme.surfaceContainer,
   ) {
       val navBackStackEntry by navController.currentBackStackEntryAsState()
       val currentRoute = navBackStackEntry?.destination?.route
       bottomNavigationItems.forEach { bottomNavigationItem ->
           NavigationBarItem(
               label = {
                   Text(
                       text = bottomNavigationItem.item?.name!!,
                       style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                   )
               },
               icon = {
                   // Icon
                   Icon(
                       painter = painterResource(id = bottomNavigationItem.item?.resId!!),
                       tint = MaterialTheme.colorScheme.secondary,
                       contentDescription = "Navigate to ${bottomNavigationItem.item.name}",
                       modifier = Modifier
                           .size(24.dp)
                           .align(Alignment.CenterVertically)
                   )
               },
               onClick = {
                   bottomNavigationItem.route.let {
                       // prevent navigating to the same screen twice
                       if (it != currentRoute) {
                           //navController.navigateTo(dest = it)
                       }
                   }
               },
               selected = currentRoute == bottomNavigationItem.route,
               colors = NavigationBarItemDefaults.colors(
                   unselectedTextColor = Color.Gray,
                   selectedTextColor = Color.Black,
                   selectedIconColor = Color.Black,
                   unselectedIconColor = Color.Black,
                   indicatorColor = Color.White
               ),
           )




       }


   }


}
