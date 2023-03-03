package com.eati.pexels.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.eati.pexels.domain.Photo


@Composable
fun PhotosScreen(viewModel: PhotosViewModel) {
    val result by viewModel.photosFlow.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(
                    Icons.Filled.Home,
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(text = "Pexels API", modifier = Modifier.padding(start = 16.dp))
            }
        },
        content = {
            Column() {
                SearchBar(viewModel::updateResults)
                PhotoCardList(result)
            }
        }
    )
}

@Composable
fun Photos(results: List<Photo>, updateResults: (String) -> Unit) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {



    }
}

@Composable
private fun PhotoCardList(results: List<Photo>) {
    LazyColumn() {
        for (item in results) {
            item {
                PhotoCard(item)
            }
        }
    }
}

@Composable
private fun SearchBar(updateResults: (String) -> Unit) {
    var input by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        TextField(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            value = input,
            onValueChange = { input = it },
            label = { Text("Search for...") },
            singleLine = true
        )

        Button(
            onClick = { updateResults(input) },
        ) {
            Text(text = "Search!")
        }
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
fun PhotoCard(photo: Photo){
    var isExpanded by remember {
        mutableStateOf(false)
    }

    Surface(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(300.dp)
    ){
        Column(
        ) {
            AnimatedVisibility(isExpanded) {
                Column(
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text(
                        text = photo.alt,
                        fontWeight = FontWeight.ExtraLight,
                        fontSize = 13.sp,
                        modifier = Modifier
                            .padding(start = 8.dp, top = 8.dp)
                    )
                    Text(
                        text = photo.photographer,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .padding(start = 8.dp)
                    )
                    Text(
                        text = photo.width.toString()+"x"+photo.height.toString(),
                        fontWeight = FontWeight.Thin,
                        fontSize = 10.sp,
                        modifier = Modifier
                            .padding(start = 8.dp, bottom = 8.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
            AsyncImage(
                model = photo.photoUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RectangleShape)
                    .clickable { isExpanded = !isExpanded }
            )

        }
    }
}

@Composable
fun Information(photo: Photo){

}