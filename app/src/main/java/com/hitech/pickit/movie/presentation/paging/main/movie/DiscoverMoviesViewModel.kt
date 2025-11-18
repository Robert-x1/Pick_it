package com.hitech.pickit.movie.presentation.paging.main.movie

import com.hitech.pickit.core.domain.repository.BasePagingRepository
import com.hitech.pickit.di.Discover
import com.hitech.pickit.movie.domain.model.Movie
import com.hitech.pickit.movie.presentation.paging.main.BaseMainPagingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DiscoverMoviesViewModel @Inject constructor(@Discover repository: BasePagingRepository<Movie>) :
    BaseMainPagingViewModel<Movie>(repository)
