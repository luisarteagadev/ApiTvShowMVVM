package com.example.apitvshowmvvm.ui.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apitvshowmvvm.data.database.TVShowsDatabase
import com.example.apitvshowmvvm.data.database.entities.TVShowEntity
import com.example.apitvshowmvvm.data.repositories.SearchTVShowRepository
import com.example.apitvshowmvvm.data.repositories.TVShowRepository
import com.example.apitvshowmvvm.data.responses.TVShowDetailResponse
import kotlinx.coroutines.launch

class WatchListViewModel(app:Application,private val tvShowRepository: TVShowRepository): ViewModel() {



    fun getAllTVShow()=tvShowRepository.gettAllTVshows()


}