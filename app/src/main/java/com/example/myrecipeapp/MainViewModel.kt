package com.example.myrecipeapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private val _categoryState =  mutableStateOf(RecipeState())
    val  categoryState : State<RecipeState> = _categoryState
}

data class RecipeState(
    val loading: Boolean = true,
    val list: List<Category> = emptyList(),
    val error: String? = null
)