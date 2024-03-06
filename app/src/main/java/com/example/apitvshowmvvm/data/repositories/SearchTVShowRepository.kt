package com.example.apitvshowmvvm.data.repositories

import com.example.apitvshowmvvm.data.network.ApiServices
import com.example.apitvshowmvvm.data.responses.TVShowsResponse

class SearchTVShowRepository {
    private val api= ApiServices()
    suspend fun getSearchTVShow(name:String,page:Int):TVShowsResponse?{
        val response=api.getSearchTVShow(name,page)
        return response
    }

}
