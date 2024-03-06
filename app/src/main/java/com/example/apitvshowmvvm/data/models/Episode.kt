package com.example.apitvshowmvvm.data.models

import com.google.gson.annotations.SerializedName

data class Episode(
    @SerializedName("air_date")
    val air_date: String,
    @SerializedName("episode")
    val episode: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("season")
    val season: Int
)