package com.example.jetpack_compose_assignment_2.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.jetpack_compose_assignment_2.ui.screen.DetailsScreen
import com.example.jetpack_compose_assignment_2.ui.screen.ListScreen
import com.example.jetpack_compose_assignment_2.ui.screen.OnboardingScreen
import com.example.jetpack_compose_assignment_2.ui.state.DetailScreenEvent
import com.example.jetpack_compose_assignment_2.ui.viewmodels.DetailScreenViewModel
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "onboarding"
    ) {
        // Onboarding Screen
        composable("onboarding") {
            OnboardingScreen(
                onContinueClicked = {
                    navController.navigate("todo/listScreen") {
                        popUpTo("onboarding") { inclusive = true }
                    }
                }
            )
        }

        // List Screen
        composable("todo/listScreen") {
            ListScreen(
                navController = navController,
                onNavigateToDetailScreen = { todoId ->
                    navController.navigate("todo/detailScreen/$todoId")
                },
            )
        }

        // Detail Screen
        composable(
            route = "todo/detailScreen/{todoId}",
            arguments = listOf(navArgument("todoId") { type = NavType.IntType })
        ) { backStackEntry ->
            val todoId = backStackEntry.arguments?.getInt("todoId") ?: 0
            val viewModel: DetailScreenViewModel = hiltViewModel()

            LaunchedEffect(todoId) {
                viewModel.handleEvent(DetailScreenEvent.OnIdChange(todoId))
                viewModel.handleEvent(DetailScreenEvent.FetchTodo)
            }

            DetailsScreen(
                detailScreenViewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}