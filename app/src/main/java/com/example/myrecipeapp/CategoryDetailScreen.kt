package com.example.myrecipeapp

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Card
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
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
            onClick = { navController.popBackStack() }){
                Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = null)
            }
        })
    },
) {
    paddingValues->

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            viewState.loading -> {
                Log.e("sssssss", "${category}")
                DetailViewModel.fetchCategoryMealList(category)
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))

            }

            viewState.error != null -> {
                Text("Error Encountered")
            }

            else -> {
                //Display Categories
                LazyColumn(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(top = paddingValues.calculateTopPadding())
                ) {
                    items(viewState.list) { meal ->
                        Box(
                            modifier = Modifier
                                .shadow(elevation = 1.dp)
                                .padding(8.dp)

                                .fillMaxSize()
                                .background(Color.Transparent),
                            contentAlignment = Alignment.Center
//                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Card(modifier = Modifier
                                .fillMaxSize()
//                                .border(2.dp, Color.Green)
                                .clip(
                                    CircleShape
                                )){
//                                Image(
//                                    painter = rememberAsyncImagePainter(meal.strMealThumb),
//                                    contentDescription = null,
////                                    modifier = Modifier.wrapContentSize()
//                                )
                                Row {
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(meal.strMealThumb).build(),
                                    contentScale = ContentScale.FillBounds,
                                    contentDescription = null,
                                    modifier = Modifier.clip(RectangleShape)
                                )
                                Log.e("LMAOO","${meal.strMealThumb}" )
                                Text(modifier = Modifier.padding(top = 100.dp),
                                   text =  meal.strMeal, textAlign = TextAlign.Center,

                                )

                                }
                            }
                            Spacer(modifier = Modifier.height(30.dp))
                        }

                    }
                }
            }
        }
    }
}

}