package com.hitech.pickit.movie.presentation.movie_fav_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hitech.pickit.core.domain.utils.NetworkError
import com.hitech.pickit.core.domain.utils.Result
import com.hitech.pickit.movie.domain.use_case.DiscoverMoviesUseCase
import com.hitech.pickit.movie.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.collections.isNotEmpty

@HiltViewModel
class DiscoverMoviesViewModel @Inject constructor(
    private val discoverMoviesUseCase: DiscoverMoviesUseCase
) : ViewModel() {

    data class MovieListUiState(
        val movies: List<Movie> = emptyList(),
        val error: NetworkError? = null,
        val isLoading: Boolean = false,
        val isLoadingMore: Boolean = false
    )

    private var currentPage = 1
    private var isLoadingMore = false
    private var hasMorePages = true

    private val _uiState = MutableStateFlow(MovieListUiState())
    val uiState = _uiState.asStateFlow()


    fun loadDiscoverMovies() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            when (val result = discoverMoviesUseCase(page = 1)) {
                is Result.Success -> {
                    currentPage = 1
                    hasMorePages = result.data.isNotEmpty()

                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            movies = result.data
                        )
                    }
                }

                is Result.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = result.error
                        )
                    }
                }
            }
        }
    }

    fun loadNextPage() {
        if (uiState.value.isLoadingMore || !hasMorePages) return

        viewModelScope.launch {

            _uiState.update { it.copy(isLoadingMore = true) }

            val nextPage = currentPage + 1

            when (val result = discoverMoviesUseCase(page = nextPage)) {
                is Result.Success -> {
                    if (result.data.isNotEmpty()) {
                        currentPage = nextPage
                        _uiState.update {
                            it.copy(
                                movies = it.movies + result.data,
                                isLoadingMore = false
                            )
                        }
                    } else {
                        hasMorePages = false
                        _uiState.update { it.copy(isLoadingMore = false) }
                    }
                }

                is Result.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoadingMore = false,
                            error = result.error
                        )
                    }
                }
            }
        }
    }
}

