package com.example.apitvshowmvvm.data.database

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.apitvshowmvvm.data.database.dao.TVShowDao
import com.example.apitvshowmvvm.data.database.entities.TVShowEntity

@Database(entities = [TVShowEntity::class], version = 1)
abstract class TVShowsDatabase:RoomDatabase() {

    abstract fun getTVShowDao(): TVShowDao

    companion object {
        @Volatile
        private var INSTANCE: TVShowsDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK) {
            INSTANCE ?: createDatabase(context).also {
                INSTANCE = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                TVShowsDatabase::class.java,
                "tvshows_db"
            ).build()
    }
       /* fun getDatabase(context: Context): TVShowsDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TVShowsDatabase::class.java,
                    "tv_shows_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }*/




}