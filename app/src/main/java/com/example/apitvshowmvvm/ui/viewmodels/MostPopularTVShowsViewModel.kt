package com.example.apitvshowmvvm.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apitvshowmvvm.data.models.TVShow
import com.example.apitvshowmvvm.data.repositories.MostPopularTVShowsRepository
import com.example.apitvshowmvvm.data.responses.TVShowsResponse
import com.example.apitvshowmvvm.domain.GetTVShows
import kotlinx.coroutines.launch

class MostPopularTVShowsViewModel() : ViewModel() {

    val tvshows = GetTVShows()
    val data = MutableLiveData<TVShowsResponse>()

    fun onCreate(page:Int){
        Log.i("tagView",page.toString())
        viewModelScope.launch {
            val result=tvshows.getTVShows(page)
            if(result!=null){
                data.postValue(result)
            }
        }
    }
}
