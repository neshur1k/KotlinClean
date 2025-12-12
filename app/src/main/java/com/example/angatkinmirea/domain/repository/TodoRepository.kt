package com.example.angatkinmirea.domain.repository

import com.example.angatkinmirea.domain.model.TodoItem

interface TodoRepository {
    suspend fun getTodos(): List<TodoItem>
    suspend fun toggleTodo(id: Int)
}
