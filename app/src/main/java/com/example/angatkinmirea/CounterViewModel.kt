package com.example.angatkinmirea
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue

class CounterViewModel : ViewModel() {
    var count by mutableIntStateOf(0)

    fun plus_count() {
        count++
    }
}