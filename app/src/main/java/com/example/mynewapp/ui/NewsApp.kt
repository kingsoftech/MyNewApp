package com.example.mynewapp.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mynewapp.MockData
import com.example.mynewapp.components.BottomMenu
import com.example.mynewapp.ui.screen.Categories
import com.example.mynewapp.ui.screen.DetailScreen
import com.example.mynewapp.ui.screen.Source
import com.example.mynewapp.ui.screen.TopNews

@Composable
fun NewsApp() {
    val scrollState = rememberScrollState()
    val navController  = rememberNavController()
    MainScreen(navController =navController , scrollState = scrollState)
}
@Composable
fun MainScreen(navController: NavHostController, scrollState: ScrollState){
    Scaffold(bottomBar={
        BottomMenu(navController = navController)
    },) {
        Navigation(navController = navController, scrollState = scrollState)
    }
}
@Composable
fun Navigation(navController: NavHostController, scrollState:ScrollState) {

    NavHost(navController= navController, startDestination = "TopNews") {
        bottomNavigation(navController= navController)
        composable("TopNews") {
            TopNews(navController = navController)
        }
        composable("Detail/{newsId}", arguments = listOf(navArgument("newsId"){type= NavType.IntType})) {
            navBackStackEntry->
            val id = navBackStackEntry.arguments?.getInt("newsId")
            val newsData = MockData.getNews(id)
            DetailScreen(navController = navController,newsData, scrollState)
        }
    }
}

fun NavGraphBuilder.bottomNavigation(navController: NavController){
    composable(BottomMenuScreen.TopNew.route){
        TopNews(navController = navController)

    }
    composable(BottomMenuScreen.Categories.route){
        Categories()

    }
    composable(BottomMenuScreen.Sources.route){
        Source()

    }
}