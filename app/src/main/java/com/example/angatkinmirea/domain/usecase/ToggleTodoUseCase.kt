package com.example.angatkinmirea.domain.usecase

import com.example.angatkinmirea.domain.repository.TodoRepository

class ToggleTodoUseCase(private val repository: TodoRepository) {
    suspend operator fun invoke(id: Int) {
        repository.toggleTodo(id)
    }
}
