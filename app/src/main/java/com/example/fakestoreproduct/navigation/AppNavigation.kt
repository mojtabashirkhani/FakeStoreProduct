package com.example.fakestoreproduct.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.feature.productDetail.productDetail
import com.example.feature.productList.productList

enum class Screen {
    HOME,
    DETAIL
}
sealed class NavigationItem(val route: String) {
    object Home : NavigationItem(Screen.HOME.name)
    object Detail : NavigationItem(Screen.DETAIL.name)
}

@Composable
fun AppNavHost(modifier: Modifier = Modifier,
               navController: NavHostController,
               startDestination: String = NavigationItem.Home.route) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(startDestination) {
            productList(
                onGoToItem = { productItem ->
                    navController.navigate("${NavigationItem.Detail.route}?productItem=$productItem")
                }
            )
        }
        composable(
            route = "${NavigationItem.Detail.route}?productItem={productItem}",
            arguments = listOf(
                navArgument("productItem") { type = NavType.StringType })
        ) { backStackEntry ->
            productDetail(
                backStackEntry.arguments?.getString("productItem") ?: "",
                onGoBack = {
                    navController.popBackStack()
                }
            )
        }

    }
}