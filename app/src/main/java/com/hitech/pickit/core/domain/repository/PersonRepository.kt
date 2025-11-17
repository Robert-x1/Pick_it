package com.hitech.pickit.core.domain.repository

import com.hitech.pickit.di.IoDispatcher
import com.hitech.pickit.movie.data.networking.dto.asDomainModel
import com.hitech.pickit.movie.data.data_source.remote.PersonService
import com.hitech.pickit.movie.domain.model.Person
import com.hitech.pickit.movie.presentation.models.base.BaseRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher

@Singleton
class PersonRepository @Inject constructor(
    private val personApi: PersonService,
    // @ApplicationContext context: Context,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
) : BaseRepository<Person>(ioDispatcher) {

    override suspend fun getSuccessResult(id: Any?): Person {

        require(id is String) { "Person ID must be a String and not null" }
        return personApi.getPerson(id).asDomainModel()
    }
}
