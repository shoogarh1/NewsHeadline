package com.shoogarh.newsheadline.utils

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.shoogarh.newsheadline.screens.ArticleWebview
import com.shoogarh.newsheadline.screens.ArticlesContent

@Composable
fun setupNavGraph(
    navController: NavHostController
){
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) { ArticlesContent(navController) }
        composable("${Screen.Detail.route}/{url}", arguments = listOf(navArgument("url"){ type = NavType.StringType})
        ) {backStackEntry ->
            backStackEntry.arguments?.getString("url").let { url ->
            ArticleWebview(navController, url = url)
            }
        }
    }
}