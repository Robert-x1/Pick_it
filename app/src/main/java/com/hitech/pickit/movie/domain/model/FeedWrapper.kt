package com.hitech.pickit.movie.domain.model

import androidx.annotation.StringRes
import com.hitech.pickit.movie.presentation.models.TMDbItem

class FeedWrapper(val feeds: List<TMDbItem>, @StringRes val sortTypeResourceId: Int, val sortType: SortType)
