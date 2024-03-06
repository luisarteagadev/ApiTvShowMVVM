package com.example.apitvshowmvvm.core.interfaces

import com.example.apitvshowmvvm.data.models.TVShow

interface RecyclerViewClickListener {
    fun onRecyclerViewItemClick(tvShow: TVShow)
}