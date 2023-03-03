package com.eati.pexels.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.eati.pexels.presentation.ui.theme.EATITheme

class MainActivity : ComponentActivity() {

    private val viewModel: PhotosViewModel by viewModels { ViewModelFactory.factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            EATITheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    PhotosScreen(viewModel)
                }
            }
        }
    }

}


