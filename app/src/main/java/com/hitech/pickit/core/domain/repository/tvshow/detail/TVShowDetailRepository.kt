package com.hitech.pickit.core.domain.repository.tvshow.detail

import com.hitech.pickit.core.domain.repository.BaseDetailRepository
import com.hitech.pickit.di.IoDispatcher
import com.hitech.pickit.movie.data.networking.dto.asCastDomainModel
import com.hitech.pickit.movie.data.networking.dto.asCrewDomainModel
import com.hitech.pickit.movie.data.networking.dto.asDomainModel
import com.hitech.pickit.movie.data.networking.dto.asTVShowDomainModel
import com.hitech.pickit.movie.data.data_source.remote.TVShowService
import com.hitech.pickit.movie.domain.model.Cast
import com.hitech.pickit.movie.domain.model.Crew
import com.hitech.pickit.movie.domain.model.TMDbImage
import com.hitech.pickit.movie.domain.model.TVShowDetails
import com.hitech.pickit.movie.presentation.models.TMDbItem
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher

@Singleton
class TVShowDetailRepository @Inject constructor(
    private val tvShowApi: TVShowService,
    //@ApplicationContext context: Context,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
) : BaseDetailRepository<TVShowDetails>(ioDispatcher) {
    override suspend fun getDetails(id: Int): TVShowDetails = tvShowApi.fetchTVSeriesDetail(id).asDomainModel()

    override suspend fun getCredit(id: Int): Pair<List<Cast>, List<Crew>> {
        val networkCreditWrapper = tvShowApi.fetchTVSeriesCredit(id)
        return Pair(
            networkCreditWrapper.cast.asCastDomainModel(),
            networkCreditWrapper.crew.asCrewDomainModel(),
        )
    }

    override suspend fun getImages(id: Int): List<TMDbImage> = tvShowApi.fetchImages(id).asDomainModel()

    override suspend fun getSimilarItems(id: Int): List<TMDbItem> =
        tvShowApi.fetchSimilarTVSeries(id).items.asTVShowDomainModel()
}
