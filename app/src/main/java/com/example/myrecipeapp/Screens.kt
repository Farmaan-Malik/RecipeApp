package com.example.myrecipeapp

sealed class Screen(val route:String) {
    object Recipe: Screen("recipeScreen")
    object Details: Screen("detailScreen")
}