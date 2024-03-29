package com.example.apitvshowmvvm.data.models


import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class TVShow(


    @SerializedName("id")
    val id:Int,
    @SerializedName("name")
    val name:String,

    @SerializedName("start_date")
    val starDate:String,

    @SerializedName("country")
    val country:String,
    @SerializedName("network")
    val network:String,
    @SerializedName("status")
    val status:String,
    @SerializedName("image_thumbnail_path")
    val thumbnail:String
)
