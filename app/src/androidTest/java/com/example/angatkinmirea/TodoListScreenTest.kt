package com.example.angatkinmirea
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.angatkinmirea.domain.model.TodoItem
import com.example.angatkinmirea.domain.repository.TodoRepository
import com.example.angatkinmirea.domain.usecase.GetTodosUseCase
import com.example.angatkinmirea.domain.usecase.ToggleTodoUseCase
import com.example.angatkinmirea.navigation.NavGraph
import com.example.angatkinmirea.presentation.ui.screen.TodoListScreen
import com.example.angatkinmirea.presentation.viewmodel.TodoViewModel
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.activity.ComponentActivity
import org.junit.Rule
import org.junit.Test

class TodoListScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun allTodosDisplayed() {
        val fakeTodos = listOf(
            TodoItem(1, "Задание 1", "Описание 1", false),
            TodoItem(2, "Задание 2", "Описание 2", true),
            TodoItem(3, "Задание 3", "Описание 3", false)
        )
        val viewModel = TodoViewModel(
            getTodosUseCase = GetTodosUseCase(object : TodoRepository {
                override suspend fun getTodos() = fakeTodos
                override suspend fun toggleTodo(id: Int) {}
            }),
            toggleTodoUseCase = ToggleTodoUseCase(object : TodoRepository {
                override suspend fun getTodos() = fakeTodos
                override suspend fun toggleTodo(id: Int) {}
            })
        )

        composeTestRule.setContent {
            TodoListScreen(viewModel = viewModel, onOpenDetail = {})
        }

        fakeTodos.forEach { todo ->
            composeTestRule.onNodeWithText(todo.title).assertIsDisplayed()
        }
    }

    @Test
    fun checkboxTogglesStatus() {
        val todos = mutableListOf(TodoItem(1, "Задание 1", "Описание 1", false))
        val repository = object : TodoRepository {
            override suspend fun getTodos() = todos
            override suspend fun toggleTodo(id: Int) {
                val index = todos.indexOfFirst { it.id == id }
                val t = todos[index]
                todos[index] = t.copy(isCompleted = !t.isCompleted)
            }
        }
        val viewModel = TodoViewModel(GetTodosUseCase(repository), ToggleTodoUseCase(repository))

        composeTestRule.setContent {
            TodoListScreen(viewModel = viewModel, onOpenDetail = {})
        }

        composeTestRule.onNodeWithText("Задание 1").performClick()
        composeTestRule.runOnIdle {
            assert(todos[0].isCompleted)
        }
    }

    @Test
    fun navigationListToDetailAndBack() {
        val todos = listOf(TodoItem(1, "Задание 1", "Описание 1", false))
        val fakeRepo = object : TodoRepository {
            override suspend fun getTodos() = todos
            override suspend fun toggleTodo(id: Int) {}
        }

        val fakeViewModel = TodoViewModel(
            getTodosUseCase = GetTodosUseCase(fakeRepo),
            toggleTodoUseCase = ToggleTodoUseCase(fakeRepo)
        )

        lateinit var navController: NavHostController

        composeTestRule.setContent {
            navController = rememberNavController()
            NavGraph(
                context = composeTestRule.activity,
                navController = navController,
                viewModel = fakeViewModel
            )
        }

        composeTestRule.onNodeWithText("Задание 1").performClick()
        composeTestRule.onNodeWithText("Задание 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Описание 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Назад").performClick()
        composeTestRule.onNodeWithText("Задание 1").assertIsDisplayed()
    }
}
