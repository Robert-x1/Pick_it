package com.hitech.pickit.movie.presentation.movieScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.AsyncImage
import com.hitech.pickit.movie.presentation.models.MovieUi
import com.hitech.pickit.movie.presentation.models.toMovieUi
import com.hitech.pickit.movie.presentation.movieScreen.components.MovieItem
import com.hitech.pickit.movie.presentation.movie_fav_list.DiscoverMoviesViewModel



@Composable
fun MovieListScreen(
    uiState: DiscoverMoviesViewModel.MovieListUiState,
    onLoadMore: () -> Unit
) {

    val listState = rememberLazyListState()

    LaunchedEffect(listState) {
        snapshotFlow {
            val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
            val totalItems = listState.layoutInfo.totalItemsCount
            lastVisibleItem to totalItems
        }.collect { (lastVisibleItem, totalItems) ->
            if (lastVisibleItem != null && lastVisibleItem >= totalItems - 3) {
                onLoadMore()
            }
        }
    }

    when {
        uiState.isLoading && uiState.movies.isEmpty() -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }


        uiState.error != null -> {
            Text(
                text = "Error: ${uiState.error.toString()}",
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center,
                color = Color.Red
            )
        }

        else -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                state = listState
            ) {
                items(uiState.movies) { movie ->
                    MovieItem(movie.toMovieUi())
                }
                if (uiState.isLoadingMore) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}