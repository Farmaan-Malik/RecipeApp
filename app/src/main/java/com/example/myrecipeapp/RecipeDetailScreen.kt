package com.example.myrecipeapp

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailScreen(
    id: Int,
    navController: NavHostController,
) {
    val viewModel: RecipeDetailScreeenViewModel = viewModel()
    val isLoading by remember { viewModel.isLoading }
    val isError by remember {
        viewModel.isError
    }
    val errorMessage by remember {
        viewModel.errorMessage
    }
    val recipe by remember {
        viewModel.recipeDetail
    }
    Log.e("id: ", id.toString())

    Scaffold(
        topBar = {
            TopAppBar(title = {
                when{
                    isLoading->Text(
                        text = "Loading",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                    )
                    else->Text(
                        text = recipe[0].strMeal,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center


                    )
                }
            }, navigationIcon = {
                IconButton(
                    onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = null)
                }
            })
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(
                    top = paddingValues.calculateTopPadding(),
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when {
                isLoading -> {
                    Log.e("Loading", isLoading.toString())
                    viewModel.getRecipe(id)
                    CircularProgressIndicator()
                }

                isError -> {
                    Text(text = errorMessage)
                }

                else -> {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxSize()
                            .verticalScroll(state = rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Card(
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)

                        ) {
                            AsyncImage(model = recipe[0].strMealThumb, contentDescription = "Meal")
                        }

                        Row(modifier = Modifier.padding(vertical = 18.dp)) {
                            Text(
                                text = "Region: ${recipe[0].strArea}",
                                modifier = Modifier.padding(end = 12.dp),
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = "Category: ${recipe[0].strCategory}",
                                fontWeight = FontWeight.Medium
                            )

                        }
                        Card(
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
                            modifier = Modifier.padding(bottom = 8.dp)
                        ) {
                            Text(
                                text = "Recipe:",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                textAlign = TextAlign.Start,
                                fontWeight = FontWeight.SemiBold
                            )

                            Text(
                                text = recipe[0].strInstructions,
                                modifier = Modifier.padding(10.dp)
                            )
                        }

                    }
                }

            }


        }

    }

}