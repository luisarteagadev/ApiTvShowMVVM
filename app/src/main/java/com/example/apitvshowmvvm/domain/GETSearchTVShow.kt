package com.example.apitvshowmvvm.domain

import com.example.apitvshowmvvm.data.repositories.SearchTVShowRepository
import com.example.apitvshowmvvm.data.responses.TVShowsResponse

class GETSearchTVShow {
    private val repository=SearchTVShowRepository()

    suspend fun getSearchTVShow(name: String,page:Int):TVShowsResponse?{
        return repository.getSearchTVShow(name,page)
    }
}