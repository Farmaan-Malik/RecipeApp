package com.example.myrecipeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myrecipeapp.ui.theme.MyRecipeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyRecipeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Screen.Recipe.route) {
                        composable(Screen.Recipe.route){
                            RecipeScreen (navController = navController)

                        }
                        composable(Screen.Details.route + "/{category}", arguments = listOf(
                            navArgument("category"){
                                type = NavType.StringType
                            }
                        )){
                            CategoryDetailScreen(category = it.arguments?.getString("category") ?: "Seafood" , navController)
                        }
                        composable(Screen.RecipeDetails.route + "/{id}", arguments = listOf(
                            navArgument("id"){
                                type = NavType.IntType
                            }
                        )){
                            RecipeDetailScreen(id = it.arguments?.getInt("id") ?: 0 , navController)
                        }
                    }
                }
                //composable("GameDetailScreen/{id}",
                //                        arguments = listOf(
                //                            navArgument("id") {
                //                                type = NavType.IntType
                //                            }
                //                        ))
            }
        }
    }
}