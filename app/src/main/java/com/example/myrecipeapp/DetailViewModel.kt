package com.example.myrecipeapp

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myrecipeapp.response.categoryMealDetails.Meal
import kotlinx.coroutines.launch
import java.lang.Exception

class DetailViewModel(): ViewModel() {
    private val _detailState =  mutableStateOf(DetailState())
    val  detailState : State<DetailState> = _detailState

     fun fetchCategoryMealList(category: String){
        viewModelScope.launch {
            try {
                Log.e("dddddddd", "asdas")
                val response = recipeService.getDetails(category)

                _detailState.value = _detailState.value.copy(
                    list = response.meals,
                    loading = false,
                    error = null
                )

            }catch (e: Exception){
                _detailState.value = _detailState.value.copy(
                    loading = false,
                    error = "Error fetchCategories ${e.message}"
                )

            }
        }
    }



}

data class DetailState(
    val loading: Boolean = true,
    val list: List<Meal> = emptyList(),
    val error: String? = null
) {
}