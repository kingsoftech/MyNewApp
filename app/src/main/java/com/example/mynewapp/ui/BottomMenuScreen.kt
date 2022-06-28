package com.example.mynewapp.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Source
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomMenuScreen(val route: String, val icon: ImageVector, val title: String) {
    object TopNew : BottomMenuScreen("top news", icon = Icons.Outlined.Home, "Top News")
    object Categories : BottomMenuScreen("Categories", icon = Icons.Outlined.Category, "Categories")
    object Sources : BottomMenuScreen("Sources", icon = Icons.Outlined.Source, "Sources")

}
