package com.example.mynewapp.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.mynewapp.R
import com.example.mynewapp.ui.BottomMenuScreen


@Composable
fun BottomMenu(navController: NavController) {
    val menuItems =
        listOf(BottomMenuScreen.TopNew, BottomMenuScreen.Categories, BottomMenuScreen.Sources)
    BottomNavigation(contentColor = colorResource(id = R.color.white)) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        menuItems.forEach {
            BottomNavigationItem(selected = currentRoute == it.route,
                alwaysShowLabel = true,
                selectedContentColor = Color.White,
                unselectedContentColor = Color.Gray,
                label = { Text(text = it.title) },
                onClick = { navController.navigate(it.route){
                    navController.graph.startDestinationRoute?.let{
                        route->
                        popUpTo(route){
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                } },
                icon = { Icon(imageVector = it.icon, contentDescription = it.title) }
            )
        }
    }
}