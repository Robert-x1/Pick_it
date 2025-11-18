package com.hitech.pickit.movie.presentation.detail_screen

import androidx.lifecycle.SavedStateHandle
import com.hitech.pickit.core.domain.repository.BaseDetailRepository
import com.hitech.pickit.core.domain.repository.BookmarkDetailsRepository
import com.hitech.pickit.movie.domain.model.Movie
import com.hitech.pickit.movie.domain.model.MovieDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    bookmarkRepository: BookmarkDetailsRepository<Movie>,
    repository: BaseDetailRepository<MovieDetails>,
    savedStateHandle: SavedStateHandle,
) : BaseDetailViewModel<MovieDetails, Movie>(bookmarkRepository, repository, savedStateHandle)
