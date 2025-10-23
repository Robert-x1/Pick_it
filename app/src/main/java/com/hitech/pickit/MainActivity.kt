package com.hitech.pickit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.hitech.pickit.movie.presentation.movie_fav_list.components.MoviePreview
import com.hitech.pickit.ui.theme.PickItTheme
import com.robert.pickit.movie.presentation.movie_list.MovieListScreen
import com.robert.pickit.movie.presentation.movie_list.MovieState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PickItTheme {

            }
        }
    }
}

