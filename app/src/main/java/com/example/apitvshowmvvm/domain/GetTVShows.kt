package com.example.apitvshowmvvm.domain

import com.example.apitvshowmvvm.data.repositories.MostPopularTVShowsRepository
import com.example.apitvshowmvvm.data.responses.TVShowsResponse

class GetTVShows {
    private val repository=MostPopularTVShowsRepository()

 /*   suspend operator fun invoke(page:Int):TVShowsResponse?{
        return repository.getAllTvShows(page)
    }*/

    suspend fun getTVShows(page:Int):TVShowsResponse?{
        return repository.getAllTvShows(page)
    }
    //CODIGO SIMILARA LA DE ARRIBA
    //suspend operator fun invoke():TVShowsResponse=repository.getAllTvShows(1)
}