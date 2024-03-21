package com.example.myrecipeapp

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter

@Composable
fun RecipeScreen(modifier: Modifier = Modifier,
                navController : NavHostController
){
    val recipeViewModel : MainViewModel = viewModel()
    val viewState by recipeViewModel.categoryState
    Box(modifier = Modifier.fillMaxSize()){
        when{
            viewState.loading ->{
                CircularProgressIndicator(modifier.align(Alignment.Center))
            }
            viewState.error != null ->{
                Text("Error Encountered")
            }
            else ->{
                //Display Categories
                CategoryScreen(categories = viewState.list, navController)
            }
        }
    }
}

@Composable
fun CategoryScreen(categories: List<Category>
                   ,navController : NavHostController){
    LazyVerticalGrid(GridCells.Fixed(2),modifier = Modifier.fillMaxSize()){
        items(categories){
            category ->
            CategoryItem(category = category,navController)

        }
    }
}

@Composable
fun CategoryItem(category: Category,
                navController : NavHostController){
   Column (modifier= Modifier
       .padding(8.dp)
       .shadow(elevation = 1.dp)
       .fillMaxSize()
       .clickable { navController.navigate(Screen.Details.route + "/${category.strCategory}") },
       horizontalAlignment = Alignment.CenterHorizontally
       ){
       Image(
           painter = rememberAsyncImagePainter(category.strCategoryThumb),
           contentDescription = null,
           modifier = Modifier
               .fillMaxSize()
               .aspectRatio(1f)
       )
       Text(text = category.strCategory,
           color = Color.Black,
           style = TextStyle(fontWeight = FontWeight.SemiBold),
           modifier = Modifier.padding(top = 8.dp)
       )
   }
}