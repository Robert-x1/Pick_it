package com.hitech.pickit.movie.presentation.paging.search

import androidx.lifecycle.SavedStateHandle
import com.hitech.pickit.core.domain.repository.BasePagingRepository
import com.hitech.pickit.di.Search
import com.hitech.pickit.movie.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchMoviesViewModel
@Inject
constructor(
    @Search repository: BasePagingRepository<Movie>,
    savedStateHandle: SavedStateHandle,
) : BaseSearchPagingViewModel<Movie>(repository, savedStateHandle)
