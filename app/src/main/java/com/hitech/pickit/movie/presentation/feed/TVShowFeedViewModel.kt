package com.hitech.pickit.movie.presentation.feed

import com.hitech.pickit.core.domain.repository.BaseFeedRepository
import com.hitech.pickit.movie.domain.model.TVShow
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TVShowFeedViewModel @Inject constructor(repository: BaseFeedRepository<TVShow>) :
    BaseFeedViewModel<TVShow>(repository)
