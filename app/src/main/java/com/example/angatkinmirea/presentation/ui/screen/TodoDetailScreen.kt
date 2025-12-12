package com.example.angatkinmirea.presentation.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.angatkinmirea.presentation.viewmodel.TodoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoDetailScreen(todoId: Int, viewModel: TodoViewModel, onBack: () -> Unit) {
    val todos by viewModel.todosState.collectAsState()
    val item by remember(todos) { derivedStateOf { todos.firstOrNull { it.id == todoId } } }

    Scaffold(topBar = { TopAppBar(title = { Text("Детали") }) }) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {
            if (item == null) {
                Text("Задача не найдена", modifier = Modifier.align(Alignment.Center))
            } else {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = item!!.title, style = MaterialTheme.typography.bodyLarge)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = item!!.description)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Выполнено: ${if (item!!.isCompleted) "Да" else "Нет"}")
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(onClick = onBack) {
                        Text("Назад")
                    }
                }
            }
        }
    }
}

