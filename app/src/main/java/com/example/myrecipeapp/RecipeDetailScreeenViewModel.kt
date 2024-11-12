package com.example.myrecipeapp

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.myrecipeapp.response.categoryMealDetails.MealX
import com.example.myrecipeapp.response.categoryMealDetails.mealDetails
import kotlinx.coroutines.launch

class RecipeDetailScreeenViewModel(
) : ViewModel() {

    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading
    private val _isError = mutableStateOf(false)
    val isError: State<Boolean> = _isError
    private val _errorMessage = mutableStateOf("")
    val errorMessage: State<String> = _errorMessage
    private val _recipeDetail = mutableStateOf<List<MealX>>(listOf())
    val recipeDetail: State<List<MealX>> = _recipeDetail


    fun getRecipe(id: Int) {
        viewModelScope.launch {
            try {
                val api = recipeService.getMealById(id)
                _recipeDetail.value = api.meals
                _isLoading.value = false
                Log.e("insideViewModel", _isLoading.value.toString())
            } catch (e: Exception) {
                Log.e("insideViewModelCatch", _isLoading.value.toString())
                _isLoading.value = false
                _isError.value = true
                _errorMessage.value = e.localizedMessage?.toString() ?: "error occurred"
            }
        }

    }
}