package com.example.angatkinmirea
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class CounterViewModel : ViewModel() {
    var count by mutableStateOf(0)
        private set

    fun plus_count() {
        count++
    }
}