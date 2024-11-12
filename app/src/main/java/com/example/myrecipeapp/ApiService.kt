package com.example.myrecipeapp

import com.example.myrecipeapp.response.categoryMealDetails.CategoryMeal
import com.example.myrecipeapp.response.categoryMealDetails.mealDetails
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private val retrofit = Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/").addConverterFactory(GsonConverterFactory.create()).build()

val recipeService = retrofit.create(ApiService::class.java)

interface ApiService {
    @GET ("categories.php")
    suspend fun getCategories(): CategoriesResponse

    @GET ("filter.php")
    suspend fun getDetails(
        @Query("c") category: String,
    ): CategoryMeal

    @GET ("lookup.php")
    suspend fun getMealById(
       @Query("i") id: Int,
    ):mealDetails
}