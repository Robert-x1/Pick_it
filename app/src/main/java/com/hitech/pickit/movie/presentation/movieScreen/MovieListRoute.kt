package com.hitech.pickit.movie.presentation.movieScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.hitech.pickit.movie.presentation.movie_fav_list.DiscoverMoviesViewModel
import com.hitech.pickit.movie.presentation.movie_fav_list.MovieFavListScreen

@Composable
fun MovieListRoute(
    viewModel: DiscoverMoviesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadDiscoverMovies()
    }

    MovieFavListScreen(
        state = uiState
    )

//    MovieListScreen(
//        uiState = uiState,
//        onLoadMore = { viewModel.loadNextPage() }
//    )
}

@PreviewLightDark
@Composable
private fun Tset() {
    MovieListRoute()

}