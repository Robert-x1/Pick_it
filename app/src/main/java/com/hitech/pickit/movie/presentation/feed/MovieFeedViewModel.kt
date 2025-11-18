package com.hitech.pickit.movie.presentation.feed


import com.hitech.pickit.core.domain.repository.BaseFeedRepository
import com.hitech.pickit.movie.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieFeedViewModel @Inject constructor(repository: BaseFeedRepository<Movie>) :
    BaseFeedViewModel<Movie>(repository)
