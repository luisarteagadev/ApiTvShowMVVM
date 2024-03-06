package com.example.apitvshowmvvm.domain

import com.example.apitvshowmvvm.data.repositories.TVShowDetailsRepository
import com.example.apitvshowmvvm.data.responses.TVShowDetailResponse


class GetTVShowDetail {

    private val repository=TVShowDetailsRepository()
    suspend fun getTVShowDetails(id:String): TVShowDetailResponse? {
        return repository.getTVShowDetails(id)
    }
}