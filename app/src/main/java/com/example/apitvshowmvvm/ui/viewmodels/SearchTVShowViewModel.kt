package com.example.apitvshowmvvm.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import com.example.apitvshowmvvm.data.responses.TVShowsResponse
import com.example.apitvshowmvvm.domain.GETSearchTVShow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SearchTVShowViewModel:ViewModel() {
    val searchTVShow=GETSearchTVShow()
    val data=MutableLiveData<TVShowsResponse>()

    fun getSearchTVShow(name:String,page:Int){
        viewModelScope.launch {

            val response=searchTVShow.getSearchTVShow(name,page)
            if(response!=null){
                data.postValue(response)
            }
        }
    }
}