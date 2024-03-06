package com.example.apitvshowmvvm.data.network

import com.example.apitvshowmvvm.data.responses.TVShowDetailResponse
import com.example.apitvshowmvvm.data.responses.TVShowsResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

    @GET("most-popular")
    suspend fun getMostPopularTVShows(@Query("page") page:Int): Response<TVShowsResponse>
    @GET("search")
    suspend fun getSearchTVShow(@Query("q") search:String, @Query("page") page:Int): Response<TVShowsResponse>
    @GET("show-details")
    suspend fun getTVShowDetails(@Query("q") id:String): Response<TVShowDetailResponse>


}