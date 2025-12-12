package com.example.angatkinmirea.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.angatkinmirea.Creator
import com.example.angatkinmirea.data.repository.TodoRepositoryImpl
import com.example.angatkinmirea.domain.model.TodoItem
import com.example.angatkinmirea.domain.usecase.GetTodosUseCase
import com.example.angatkinmirea.domain.usecase.ToggleTodoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TodoViewModel(
    private val getTodosUseCase: GetTodosUseCase,
    private val toggleTodoUseCase: ToggleTodoUseCase
) : ViewModel() {
    private val _todosState = MutableStateFlow<List<TodoItem>>(emptyList())
    val todosState: StateFlow<List<TodoItem>> = _todosState.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadTodos()
    }

    fun loadTodos() {
        viewModelScope.launch {
            _isLoading.value = true
            _todosState.value = getTodosUseCase()
            _isLoading.value = false
        }
    }

    fun onToggleTodo(id: Int) {
        viewModelScope.launch {
            toggleTodoUseCase(id)
            loadTodos()
        }
    }

    class TodoViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            val repository = TodoRepositoryImpl.getInstance(context)
            val getTodosUseCase = GetTodosUseCase(repository)
            val toggleTodoUseCase = ToggleTodoUseCase(repository)
            return TodoViewModel(getTodosUseCase, toggleTodoUseCase) as T
        }
    }
}
