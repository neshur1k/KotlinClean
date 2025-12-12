package com.example.angatkinmirea.data.repository

import android.content.Context
import com.example.angatkinmirea.data.local.TodoJsonDataSource
import com.example.angatkinmirea.domain.model.TodoItem
import com.example.angatkinmirea.domain.repository.TodoRepository

class TodoRepositoryImpl private constructor(private val context: Context) : TodoRepository {

    private val dataSource = TodoJsonDataSource(context)
    private val todos = mutableListOf<TodoItem>()

    private fun ensureLoaded() {
        if (todos.isEmpty()) {
            val dtos = dataSource.getTodos()
            todos.addAll(dtos.map { dto ->
                TodoItem(
                    id = dto.id,
                    title = dto.title,
                    description = dto.description,
                    isCompleted = dto.isCompleted
                )
            })
        }
    }

    override suspend fun getTodos(): List<TodoItem> {
        ensureLoaded()
        return todos.map { it.copy() }
    }

    override suspend fun toggleTodo(id: Int) {
        ensureLoaded()
        val idx = todos.indexOfFirst { it.id == id }
        if (idx >= 0) {
            val old = todos[idx]
            todos[idx] = old.copy(isCompleted = !old.isCompleted)
        }
    }

    companion object {
        @Volatile
        private var instance: TodoRepositoryImpl? = null

        fun getInstance(context: Context): TodoRepositoryImpl =
            instance ?: synchronized(this) {
                instance ?: TodoRepositoryImpl(context).also { instance = it }
            }
    }
}
