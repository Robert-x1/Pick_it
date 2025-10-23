package com.robert.pickit.movie.presentation.movie_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hitech.pickit.R
import com.hitech.pickit.movie.presentation.movie_fav_list.components.MovieListItem
import com.hitech.pickit.movie.presentation.movie_fav_list.components.MoviePreview
import com.hitech.pickit.ui.theme.PickItTheme


@Composable
fun MovieListScreen(
    state: MovieState,
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    if (state.isLoading) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface),
            contentAlignment = Alignment.Center
        ) {
            //shimmer
            CircularProgressIndicator()
        }

    } else {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface),
            contentAlignment = Alignment.Center
        ) {

            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(R.drawable.joker),
                contentDescription = "",
                contentScale = ContentScale.Crop, alignment = Alignment.TopCenter
            )
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                MaterialTheme.colorScheme.surface
                            ), startY = 300f,
                            endY = 1200f
                        )
                    )
            )
            LazyRow(
                modifier = Modifier,
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ) {
                items(
                    state.movies,
                    key = { it.id }
                ) { movie ->
                    MovieListItem(
                        movieUi = movie,
                        onclick = {},
                        screenHeight = screenHeight,
                        modifier = Modifier
                            .padding(start = 16.dp, top = 50.dp)
                            .height(screenHeight * 0.9f)
                            .width(screenWidth * 0.9f)
                    )
                }
            }
        }


    }

}


@Preview
@Composable
private fun MovieFavListScreenPreview() {
    PickItTheme {
        MovieListScreen(
            state = MovieState(
                movies = (1..5).map {
                    MoviePreview.copy(id = it.toString())
                }

            )
        )
    }

}