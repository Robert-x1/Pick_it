package com.hitech.pickit.movie.presentation.paging.main.tvshow


import com.hitech.pickit.core.domain.repository.BasePagingRepository
import com.hitech.pickit.di.Trending
import com.hitech.pickit.movie.domain.model.TVShow
import com.hitech.pickit.movie.presentation.paging.main.BaseMainPagingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TrendingTvSeriesViewModel @Inject constructor(@Trending repository: BasePagingRepository<TVShow>) :
    BaseMainPagingViewModel<TVShow>(repository)
