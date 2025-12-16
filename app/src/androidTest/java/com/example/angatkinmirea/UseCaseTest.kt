package com.example.angatkinmirea
import com.example.angatkinmirea.domain.model.TodoItem
import com.example.angatkinmirea.domain.repository.TodoRepository
import com.example.angatkinmirea.domain.usecase.GetTodosUseCase
import com.example.angatkinmirea.domain.usecase.ToggleTodoUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class UseCaseTest {
    @Test
    fun returns3todos() = runBlocking {
        val fakeTodos = listOf(
            TodoItem(1, "Задание 1", "Описание 1", false),
            TodoItem(2, "Задание 2", "Описание 2", true),
            TodoItem(3, "Задание 3", "Описание 3", false)
        )
        val repository = object : TodoRepository {
            override suspend fun getTodos() = fakeTodos
            override suspend fun toggleTodo(id: Int) {}
        }

        val useCase = GetTodosUseCase(repository)
        val todos = useCase()
        assertEquals(3, todos.size)
    }

    @Test
    fun changesIsCompleted() = runBlocking {
        val todos = mutableListOf(
            TodoItem(1, "Задание 1", "Описание 1", false)
        )
        val repository = object : TodoRepository {
            override suspend fun getTodos() = todos
            override suspend fun toggleTodo(id: Int) {
                val index = todos.indexOfFirst { it.id == id }
                if (index >= 0) {
                    val t = todos[index]
                    todos[index] = t.copy(isCompleted = !t.isCompleted)
                }
            }
        }

        val useCase = ToggleTodoUseCase(repository)
        useCase(1)
        assertTrue(todos[0].isCompleted)
    }
}
