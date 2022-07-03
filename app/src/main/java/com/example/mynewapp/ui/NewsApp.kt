package com.example.mynewapp.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mynewapp.components.BottomMenu
import com.example.mynewapp.network.NewsManager
import com.example.mynewapp.network.models.TopNewsArticle
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
        Navigation(navController = navController, scrollState = scrollState, paddingValues = it)
    }
}
@Composable
fun Navigation(navController: NavHostController, scrollState:ScrollState, newsManager: NewsManager = NewsManager(),paddingValues:PaddingValues) {
    val articles = newsManager.newsResponse.value.articles
    NavHost(navController= navController, startDestination = BottomMenuScreen.TopNew.route, modifier = Modifier.padding(paddingValues = paddingValues)) {
        if (articles != null) {
            bottomNavigation(navController= navController,articles)
        }
        composable("TopNews") {
            if (articles != null) {
                TopNews(navController = navController,articles)
            }
        }
        composable("Detail/{index}", arguments = listOf(navArgument("index"){type= NavType.IntType})) {
            navBackStackEntry->
            val index = navBackStackEntry.arguments?.getInt("index")
            index?.let {
                val article = articles?.get(it)
                DetailScreen(article!!,navController = navController, scrollState = scrollState)
            }

        }
    }
}

fun NavGraphBuilder.bottomNavigation(navController: NavController, articles:List<TopNewsArticle>){
    composable(BottomMenuScreen.TopNew.route){
        TopNews(navController = navController, articles)

    }
    composable(BottomMenuScreen.Categories.route){
        Categories()

    }
    composable(BottomMenuScreen.Sources.route){
        Source()

    }
}