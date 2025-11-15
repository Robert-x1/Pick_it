package com.hitech.pickit.core.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hitech.pickit.core.data.source.entity.MovieEntity
import com.hitech.pickit.core.data.source.entity.TVShowEntity
import com.hitech.pickit.movie.utili.db.Converters

@Database(entities = [MovieEntity::class, TVShowEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class TMDbDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao
    abstract val tvShowDao: TVShowDao
}
