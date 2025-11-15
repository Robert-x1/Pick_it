package com.hitech.pickit.core.domain.repository.movie.detail

import com.hitech.pickit.core.domain.repository.BaseDetailRepository
import com.hitech.pickit.di.IoDispatcher
import com.hitech.pickit.movie.data.networking.dto.asCastDomainModel
import com.hitech.pickit.movie.data.networking.dto.asCrewDomainModel
import com.hitech.pickit.movie.data.networking.dto.asDomainModel
import com.hitech.pickit.movie.data.networking.dto.asMovieDomainModel
import com.hitech.pickit.movie.data.remote.MovieService
import com.hitech.pickit.movie.domain.model.Cast
import com.hitech.pickit.movie.domain.model.Crew
import com.hitech.pickit.movie.domain.model.MovieDetails
import com.hitech.pickit.movie.domain.model.TMDbImage
import com.hitech.pickit.movie.presentation.models.TMDbItem
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher

@Singleton
class MovieDetailRepository @Inject constructor(
    private val movieApi: MovieService,
   // @ApplicationContext context: Context,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
) : BaseDetailRepository<MovieDetails>(ioDispatcher) {
    override suspend fun getDetails(id: Int): MovieDetails = movieApi.fetchMovieDetail(id).asDomainModel()

    override suspend fun getCredit(id: Int): Pair<List<Cast>, List<Crew>> {
        val networkCreditWrapper = movieApi.movieCredit(id)
        return Pair(
            networkCreditWrapper.cast.asCastDomainModel(),
            networkCreditWrapper.crew.asCrewDomainModel(),
        )
    }

    override suspend fun getImages(id: Int): List<TMDbImage> = movieApi.fetchImages(id).asDomainModel()

    override suspend fun getSimilarItems(id: Int): List<TMDbItem> =
        movieApi.fetchSimilarMovies(id).items.asMovieDomainModel()
}
