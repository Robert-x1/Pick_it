package com.hitech.pickit.core.domain.repository.tvshow.detail

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.hitech.pickit.core.data.mapper.TvShowMapper.toFirestoreMap
import com.hitech.pickit.core.data.mapper.TvShowMapper.toTVShow
import com.hitech.pickit.core.domain.repository.BookmarkDetailsRepository
import com.hitech.pickit.movie.domain.model.TVShow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreBookmarkTVShowDetailsRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : BookmarkDetailsRepository<TVShow> {

    private fun getUserTVBookmarksCollection() =
        auth.currentUser?.uid?.let { uid ->
            firestore.collection("users").document(uid).collection("bookmarks_tv_shows")
        }

    override suspend fun addBookmark(item: TVShow) {
        val collection = getUserTVBookmarksCollection() ?: return
        collection.document(item.id.toString())
            .set(item.toFirestoreMap())
            .await()
    }

    override suspend fun deleteBookmark(id: Int) {
        val collection = getUserTVBookmarksCollection() ?: return
        collection.document(id.toString()).delete().await()
    }

    override suspend fun isBookmarked(id: Int): Boolean {
        val collection = getUserTVBookmarksCollection() ?: return false
        val snapshot = collection.document(id.toString()).get().await()
        return snapshot.exists()
    }

    override suspend fun getBookmarks(): List<TVShow> {
        val collection = getUserTVBookmarksCollection() ?: return emptyList()

        val snapshot = collection
            .orderBy("savedAt", Query.Direction.DESCENDING)
            .get()
            .await()

        return snapshot.documents.map { it.toTVShow() }
    }

    override fun isUserLoggedIn(): Boolean {
        return auth.currentUser != null
    }
}