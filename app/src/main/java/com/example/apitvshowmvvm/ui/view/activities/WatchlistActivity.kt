package com.example.apitvshowmvvm.ui.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.apitvshowmvvm.R
import com.example.apitvshowmvvm.data.database.TVShowsDatabase
import com.example.apitvshowmvvm.data.repositories.TVShowRepository
import com.example.apitvshowmvvm.databinding.ActivityWatchlistBinding
import com.example.apitvshowmvvm.ui.viewmodels.WatchListViewModel
import com.example.apitvshowmvvm.ui.viewmodels.WatchListViewModelFactory


class WatchlistActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWatchlistBinding
    private lateinit var watchListViewModel:WatchListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityWatchlistBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupViewModel()

    }


    private fun setupViewModel(){
        val tvShowRepository=TVShowRepository(TVShowsDatabase(this))
        val viewModelProviderFactory=WatchListViewModelFactory(application,tvShowRepository)
        watchListViewModel=ViewModelProvider(this,viewModelProviderFactory)[WatchListViewModel::class.java]
    }

    private fun updateUI(){

    }

    private fun loadAllTVShows(){
        watchListViewModel.getAllTVShow().observe(this, Observer { tvShows->

        })
    }
}