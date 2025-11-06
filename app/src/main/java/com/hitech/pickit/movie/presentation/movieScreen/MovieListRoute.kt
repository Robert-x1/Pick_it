package com.hitech.pickit.movie.presentation.movieScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.hitech.pickit.movie.presentation.movieScreen.components.MovieListScreen
import com.hitech.pickit.movie.presentation.movie_fav_list.DiscoverMoviesViewModel

@Composable
fun MovieListRoute(
    viewModel: DiscoverMoviesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadDiscoverMovies()
    }

    MovieListScreen(
        uiState = uiState,
        onLoadMore = { viewModel.loadNextPage() }
    )
}