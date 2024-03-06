package com.example.apitvshowmvvm.data.responses

import com.example.apitvshowmvvm.data.models.TVShowDetailsModel
import com.google.gson.annotations.SerializedName

data class TVShowDetailResponse(
    @SerializedName("tvShow")
    val tvShows: TVShowDetailsModel

)
