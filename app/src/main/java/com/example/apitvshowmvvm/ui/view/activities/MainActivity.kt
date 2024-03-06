package com.example.apitvshowmvvm.ui.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apitvshowmvvm.core.adapter.TVShowMostPopularAdapter
import com.example.apitvshowmvvm.core.interfaces.RecyclerViewClickListener
import com.example.apitvshowmvvm.data.models.TVShow
import com.example.apitvshowmvvm.databinding.ActivityMainBinding
import com.example.apitvshowmvvm.data.responses.TVShowsResponse
import com.example.apitvshowmvvm.ui.viewmodels.MostPopularTVShowsViewModel
import com.google.gson.Gson

class MainActivity : AppCompatActivity(), RecyclerViewClickListener {


    private lateinit var binding:ActivityMainBinding
    private val tvShowsViewModel: MostPopularTVShowsViewModel by viewModels()
    private var lstTVShows: MutableList<TVShow> = mutableListOf()
    private var currentPage:Int=1
    private  var totalAvailablePage:Int=1
    private lateinit var adapter:TVShowMostPopularAdapter
    private var newListTVShows:MutableList<TVShow> = mutableListOf()
    //private val viewModel:MostPopularTVShowsViewModel by viewModels()
    private  var positionStart=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        adapter=TVShowMostPopularAdapter(newListTVShows,this)


        // BLOQUE DE CODIGO PARA OBSERVAR EL CAMBIO DE MI DATA
        tvShowsViewModel.data.observe(this, Observer { it ->
            Log.i("tagLista","observer")
            toggleLoading()
            if (it != null && it!=TVShowsResponse(0,0, emptyList())) {
                totalAvailablePage = it.pages
                if (it.tvShows != null ) {
                    newListTVShows=it.tvShows.toMutableList()

                    Log.i("tagLista",newListTVShows.toString())

                    if(currentPage==1){
                        binding.tvShowsRecyclerView.layoutManager = LinearLayoutManager(this)
                        binding.tvShowsRecyclerView.adapter = adapter
                        binding.tvShowsRecyclerView.setHasFixedSize(true)
                        adapter.updateTVShows(newListTVShows)
                    }

                    if(currentPage!=1 ){
                        adapter.updateTVShows(newListTVShows)
                    }
                }
            }

        })



        doInitialization()
        //EVENTOS DEL SCROLL AL BAJAR
        binding.tvShowsRecyclerView.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val canScrollDown = recyclerView.canScrollVertically(1)
                if (!canScrollDown) {
                    currentPage+=1
                    Log.i("ScrollListener",currentPage.toString())
                    getMostPopularTVShows(currentPage)
                }


            }
        })

        binding.imageSearch.setOnClickListener {
            val intent=Intent(this,SearchActivity::class.java)
            startActivity(intent)
        }
    }

    fun doInitialization(){
        Log.i("tagLista","do initialization")
        getMostPopularTVShows(currentPage)
    }

    fun getMostPopularTVShows(page:Int) {
        Log.i("tagLista","fun getMostPopular")
        tvShowsViewModel.onCreate(page)
        toggleLoading()
    }

    fun toggleLoading(){
        if(currentPage==1){
            binding.isLoading = binding.isLoading != true
        }else{
            binding.isLoadingMore = binding.isLoadingMore != true
        }
    }

    override fun onRecyclerViewItemClick(tvShow: TVShow) {
        val gson = Gson()
        val tvshow = gson.toJson(tvShow)
        val intent= Intent(this,DetailsTVShowActivity::class.java)
        with(intent) {
            putExtra("tvshow",tvshow)
            putExtra("id",tvShow.id.toString())
            putExtra("name",tvShow.name)
            putExtra("startDate",tvShow.starDate)
            putExtra("country",tvShow.country)
            putExtra("network",tvShow.network)
            putExtra("status",tvShow.status)


        }
        startActivity(intent)

    }


}