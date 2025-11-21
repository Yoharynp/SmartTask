package com.example.smarttask.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smarttask.ui.features.home.HomeScreen
import com.example.smarttask.ui.features.login.LoginScreen


@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.Login.route
    ) {
        composableData(
            route = Routes.Login.route,
            content = {
            LoginScreen(
                onNavigateHome = {
                    navController.navigate(Routes.Home.route)
                },
                onNavigateRegister = {
                    navController.navigate(Routes.Register.route)
                }
            )
        })
        composableData(
            route =Routes.Home.route,
            content = {
                HomeScreen(
                    onNavigateLogOut = {
                        navController.navigate(Routes.Login.route)
                    }
                )
            }
        )
    }
}

private fun NavGraphBuilder.composableData(route: String, content: @Composable () -> Unit) {
    composable(
        route,
        enterTransition = {
            slideInHorizontally(initialOffsetX = { -1000 })
        },
        exitTransition = {
            slideOutHorizontally(targetOffsetX  = {1000})
        }
    ) {
        content()
    }
}