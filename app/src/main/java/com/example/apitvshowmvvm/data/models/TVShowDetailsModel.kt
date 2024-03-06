package com.example.apitvshowmvvm.data.models

import com.google.gson.annotations.SerializedName

data class TVShowDetailsModel(
    @SerializedName("country")
    val country: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("description_source")
    val description_source: String,
    @SerializedName("end_date")
    val end_date: Any,
    @SerializedName("genres")
    val genres: List<String>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image_path")
    val image_path: String,
    @SerializedName("image_thumbnail_path")
    val image_thumbnail_path: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("network")
    val network: String,
    @SerializedName("permalink")
    val permalink: String,
    @SerializedName("pictures")
    val pictures: List<String>,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("rating_count")
    val rating_count: String,
    @SerializedName("runtime")
    val runtime: Int,
    @SerializedName("start_date")
    val start_date: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("youtube_link")
    val youtube_link: Any,
    @SerializedName("countdown")
    val countdown: Any,
    @SerializedName("episodes")
    val episodes: List<Episode>,
)
