package com.hitech.pickit.movie.presentation.feed

import com.hitech.pickit.core.domain.repository.BaseFeedRepository
import com.hitech.pickit.movie.domain.model.FeedWrapper
import com.hitech.pickit.movie.presentation.models.TMDbItem
import com.hitech.pickit.movie.presentation.models.base.TMDbViewModel

open class BaseFeedViewModel<T : TMDbItem>(repository: BaseFeedRepository<T>) :
    TMDbViewModel<List<FeedWrapper>>(repository)
