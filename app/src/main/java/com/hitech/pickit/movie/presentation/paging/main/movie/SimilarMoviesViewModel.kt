package com.hitech.pickit.movie.presentation.paging.main.movie

import androidx.lifecycle.SavedStateHandle
import com.hitech.pickit.core.domain.repository.BasePagingRepository
import com.hitech.pickit.di.Similar
import com.hitech.pickit.movie.domain.model.Movie
import com.hitech.pickit.movie.utili.MainDestinations
import com.hitech.pickit.movie.presentation.paging.main.BaseMainPagingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SimilarMoviesViewModel @Inject constructor(
    @Similar repository: BasePagingRepository<Movie>,
    savedStateHandle: SavedStateHandle,
) : BaseMainPagingViewModel<Movie>(repository, savedStateHandle[MainDestinations.TMDB_SIMILAR_ID])
