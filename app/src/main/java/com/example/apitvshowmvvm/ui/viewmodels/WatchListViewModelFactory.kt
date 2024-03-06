package com.example.apitvshowmvvm.ui.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.apitvshowmvvm.data.repositories.TVShowRepository

class WatchListViewModelFactory(val app:Application, private val tvShowRepository: TVShowRepository) :ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return WatchListViewModel(app,tvShowRepository) as T
    }

}