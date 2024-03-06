package com.example.apitvshowmvvm.data.responses

import com.example.apitvshowmvvm.data.models.TVShow
import com.google.gson.annotations.SerializedName

data class TVShowsResponse(
    @SerializedName("page")
    val page:Int,
    @SerializedName("pages")
    val pages:Int,
    @SerializedName("tv_shows")
    val tvShows:List<TVShow>


)
