package com.example.apitvshowmvvm.data.repositories

import com.example.apitvshowmvvm.data.network.ApiServices
import com.example.apitvshowmvvm.data.responses.TVShowDetailResponse
import com.example.apitvshowmvvm.data.responses.TVShowsResponse

class TVShowDetailsRepository {
    private val api= ApiServices()

    suspend fun getTVShowDetails(idTVShow:String): TVShowDetailResponse?{
        return api.getTVShowDetails(idTVShow)
    }


}