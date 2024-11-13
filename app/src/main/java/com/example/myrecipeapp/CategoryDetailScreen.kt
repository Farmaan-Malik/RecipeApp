package com.example.myrecipeapp

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDetailScreen(category: String, navController: NavController) {
    val DetailViewModel: DetailViewModel = viewModel()
    val viewState by DetailViewModel.detailState
    Scaffold(
        topBar = {
            TopAppBar(title = { }, navigationIcon = {
                IconButton(
                    onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = null)
                }
            })
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when {
                viewState.loading -> {
                    Log.e("sssssss", "${category}")
                    DetailViewModel.fetchCategoryMealList(category)
                    CircularProgressIndicator()

                }

                viewState.error != null -> {
                    Text("Error Encountered")
                }

                else -> {
                    //Display Categories
                    LazyColumn(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(top = paddingValues.calculateTopPadding())
                    ) {
                        items(viewState.list) { meal ->
                            Card(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(12.dp),
                                colors = CardDefaults.cardColors(containerColor = Color.White),
                                elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
                                border = BorderStroke(
                                    2.dp, brush = Brush.linearGradient(
                                        listOf(
                                            Color.Red,
                                            Color.Black,
                                            Color.Green,
                                            Color.Blue,
                                            Color.Yellow
                                        )
                                    )
                                ),
                                onClick = { navController.navigate(Screen.RecipeDetails.route + "/${meal.idMeal}") }

                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {

                                    AsyncImage(
                                        model = meal.strMealThumb,
                                        contentDescription = "meal",
                                        contentScale = ContentScale.FillBounds
                                    )
                                    Log.e("LMAOO", "${meal.strMealThumb}")

                                }

                            }
                            Text(
                                text = meal.strMeal,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .padding(bottom = 20.dp)
                            )
                        }
                    }

                }
            }
        }
    }
}