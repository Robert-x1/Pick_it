package com.hitech.pickit.movie.presentation.detail_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.hitech.pickit.core.domain.repository.BaseDetailRepository
import com.hitech.pickit.core.domain.repository.BookmarkDetailsRepository
import com.hitech.pickit.movie.domain.model.DetailWrapper
import com.hitech.pickit.movie.domain.model.TMDbItemDetails
import com.hitech.pickit.movie.presentation.models.TMDbItem
import com.hitech.pickit.movie.presentation.models.base.TMDbViewModel
import com.hitech.pickit.movie.utili.MainDestinations
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import com.hitech.pickit.R

open class BaseDetailViewModel<T : TMDbItemDetails, R : TMDbItem>(
    private val bookmarkRepository: BookmarkDetailsRepository<R>,
    repository: BaseDetailRepository<T>,
    savedStateHandle: SavedStateHandle,
) : TMDbViewModel<DetailWrapper>(
    repository,
    savedStateHandle[MainDestinations.TMDB_ID_KEY],
) {
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    private val _isBookmarked = MutableStateFlow(false)
    val isBookmarked: StateFlow<Boolean>
        get() = _isBookmarked

    fun addBookmark(item: R) = viewModelScope.launch {
        if (!bookmarkRepository.isUserLoggedIn()) {
            _uiEvent.send(UiEvent.ShowToastMessage(R.string.login_required_toast))
            return@launch
        }

        bookmarkRepository.addBookmark(item)
        isBookmarked(item.id)
    }

    fun removeBookmark(id: Int) = viewModelScope.launch {
        if (!bookmarkRepository.isUserLoggedIn()) return@launch

        bookmarkRepository.deleteBookmark(id)
        isBookmarked(id)
    }

    fun isBookmarked(id: Int) = viewModelScope.launch {
        _isBookmarked.emit(bookmarkRepository.isBookmarked(id))
    }
}

sealed class UiEvent {
    data class ShowToastMessage(val messageResId: Int) : UiEvent()
}
