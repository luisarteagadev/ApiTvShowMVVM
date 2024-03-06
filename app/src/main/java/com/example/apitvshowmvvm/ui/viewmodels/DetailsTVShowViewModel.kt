package com.example.apitvshowmvvm.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apitvshowmvvm.data.database.TVShowsDatabase
import com.example.apitvshowmvvm.data.database.entities.TVShowEntity
import com.example.apitvshowmvvm.data.repositories.TVShowRepository
import com.example.apitvshowmvvm.data.responses.TVShowDetailResponse
import com.example.apitvshowmvvm.domain.GetTVShowDetail
import kotlinx.coroutines.launch

class DetailsTVShowViewModel : ViewModel() {
    val detailTVShow=GetTVShowDetail()
    val data=MutableLiveData<TVShowDetailResponse>()

    fun getDetails(id:String){
        viewModelScope.launch{
            val response=detailTVShow.getTVShowDetails(id)
            if(response!=null){
                data.postValue(response)
            }
        }

    }

    fun addToWatchList(tvshow:TVShowEntity,tvShowRepository: TVShowRepository){
        viewModelScope.launch {
            tvShowRepository.insertTVShow(tvshow)
        }

    }

}