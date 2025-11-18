package com.hitech.pickit.movie.presentation.models.base

open class TMDbViewModel<T>(
    repository: BaseRepository<T>,
    id: Any? = null
) : BaseViewModel<T>(repository, id)
