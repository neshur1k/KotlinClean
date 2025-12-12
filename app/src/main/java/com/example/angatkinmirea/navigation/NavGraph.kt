package com.example.angatkinmirea.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.angatkinmirea.presentation.ui.screen.TodoDetailScreen
import com.example.angatkinmirea.presentation.ui.screen.TodoListScreen
import com.example.angatkinmirea.presentation.viewmodel.TodoViewModel

sealed class Screen(val route: String) {
    object List : Screen("list")
    object Detail : Screen("detail/{todoId}") {
        fun createRoute(todoId: Int) = "detail/$todoId"
    }
}

@Composable
fun NavGraph(
    startDestination: String = Screen.List.route,
    context: Context,
    navController: NavHostController? = null,
    viewModel: TodoViewModel? = null
) {
    val nav = navController ?: rememberNavController()
    val vm = viewModel ?: androidx.lifecycle.viewmodel.compose.viewModel(
        factory = TodoViewModel.TodoViewModelFactory(context)
    )

    NavHost(navController = nav, startDestination = startDestination) {
        composable(Screen.List.route) {
            TodoListScreen(viewModel = vm, onOpenDetail = { id ->
                nav.navigate(Screen.Detail.createRoute(id))
            })
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("todoId") { type = NavType.IntType })
        ) { backStackEntry ->
            val todoId = backStackEntry.arguments?.getInt("todoId") ?: -1
            TodoDetailScreen(todoId = todoId, viewModel = vm, onBack = { nav.popBackStack() })
        }
    }
}

