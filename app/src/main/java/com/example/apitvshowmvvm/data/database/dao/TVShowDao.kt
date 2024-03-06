package com.example.apitvshowmvvm.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.apitvshowmvvm.data.database.entities.TVShowEntity

@Dao
interface TVShowDao {

    @Query("SELECT * FROM tv_shows_table")
    fun getWatchList():LiveData<List<TVShowEntity>>



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToWatchList(tvShow:TVShowEntity)

    @Delete
    suspend fun removeFromWatchlist(tvShow: TVShowEntity)
}