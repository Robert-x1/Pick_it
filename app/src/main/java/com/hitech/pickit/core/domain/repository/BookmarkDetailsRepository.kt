package com.hitech.pickit.core.domain.repository

import com.hitech.pickit.movie.presentation.models.TMDbItem


interface BookmarkDetailsRepository<T : TMDbItem> {
    suspend fun addBookmark(item: T)

    suspend fun deleteBookmark(id: Int)

    suspend fun isBookmarked(id: Int): Boolean

    suspend fun getBookmarks(): List<T>
}
