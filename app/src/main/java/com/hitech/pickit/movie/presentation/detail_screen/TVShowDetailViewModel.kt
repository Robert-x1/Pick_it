package com.hitech.pickit.movie.presentation.detail_screen

import androidx.lifecycle.SavedStateHandle
import com.hitech.pickit.core.domain.repository.BaseDetailRepository
import com.hitech.pickit.core.domain.repository.BookmarkDetailsRepository
import com.hitech.pickit.movie.domain.model.TVShow
import com.hitech.pickit.movie.domain.model.TVShowDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TVShowDetailViewModel @Inject constructor(
    bookmarkRepository: BookmarkDetailsRepository<TVShow>,
    repository: BaseDetailRepository<TVShowDetails>,
    savedStateHandle: SavedStateHandle,
) : BaseDetailViewModel<TVShowDetails, TVShow>(bookmarkRepository, repository, savedStateHandle)
