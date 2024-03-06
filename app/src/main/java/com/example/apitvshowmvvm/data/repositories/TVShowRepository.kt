package com.example.apitvshowmvvm.data.repositories

import com.example.apitvshowmvvm.data.database.TVShowsDatabase
import com.example.apitvshowmvvm.data.database.entities.TVShowEntity

class TVShowRepository(private val db:TVShowsDatabase) {

    suspend fun insertTVShow(tvShow:TVShowEntity)=db.getTVShowDao().addToWatchList(tvShow)

    fun gettAllTVshows()=db.getTVShowDao().getWatchList()

}