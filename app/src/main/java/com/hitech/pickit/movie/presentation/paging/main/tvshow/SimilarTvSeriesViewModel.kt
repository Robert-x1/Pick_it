package com.hitech.pickit.movie.presentation.paging.main.tvshow

import androidx.lifecycle.SavedStateHandle
import com.hitech.pickit.core.domain.repository.BasePagingRepository
import com.hitech.pickit.di.Similar
import com.hitech.pickit.movie.domain.model.TVShow
import com.hitech.pickit.movie.utili.MainDestinations
import com.hitech.pickit.movie.presentation.paging.main.BaseMainPagingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SimilarTvSeriesViewModel @Inject constructor(
    @Similar repository: BasePagingRepository<TVShow>,
    savedStateHandle: SavedStateHandle,
) : BaseMainPagingViewModel<TVShow>(repository, savedStateHandle[MainDestinations.TMDB_SIMILAR_ID])
