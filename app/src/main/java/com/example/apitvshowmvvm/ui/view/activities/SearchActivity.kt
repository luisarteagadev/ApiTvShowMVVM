package com.example.apitvshowmvvm.ui.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apitvshowmvvm.R
import com.example.apitvshowmvvm.core.adapter.TVShowMostPopularAdapter
import com.example.apitvshowmvvm.core.interfaces.RecyclerViewClickListener
import com.example.apitvshowmvvm.data.models.TVShow
import com.example.apitvshowmvvm.data.responses.TVShowsResponse
import com.example.apitvshowmvvm.databinding.ActivityMainBinding
import com.example.apitvshowmvvm.databinding.ActivitySearchBinding
import com.example.apitvshowmvvm.ui.viewmodels.SearchTVShowViewModel
import java.util.Timer
import java.util.TimerTask


class SearchActivity : AppCompatActivity(), RecyclerViewClickListener {

    private lateinit var binding:ActivitySearchBinding
    private val searchTVShowViewModel:SearchTVShowViewModel by viewModels()
    private var lstTVShows: MutableList<TVShow> = mutableListOf()
    private lateinit var adapter: TVShowMostPopularAdapter
    private var newListTVShows:MutableList<TVShow> = mutableListOf()
    private var currentPage:Int=1
    private  var totalAvailablePage:Int=1
    private lateinit var timer:Timer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        doIntialization()

        searchTVShowViewModel.data.observe(this, Observer {
                it ->
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

        binding.imageSearch.setOnClickListener {
            Log.i("TagSearch", "le di click al busacar")
            val peli=binding.inputSearch.text.toString()
            searchTVShow(peli)
        }



    }

    fun doIntialization(){
        adapter=TVShowMostPopularAdapter(newListTVShows,this)
        timer=Timer()
        //TO BTN BACK
        binding.imageBack.setOnClickListener{
            finish()
        }

        binding.inputSearch.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.i("tagText","No cancele proceso")
                if(timer!=null){
                    Log.i("tagText","Cancele proceso")
                    timer.cancel()
                }
            }

            override fun afterTextChanged(s: Editable?) {
                Log.i("tagText","EN EL IF")
                if(s.toString().trim().isNotEmpty()){
                    timer = Timer()
                    timer.schedule(object : TimerTask() {
                        override fun run() {
                            Handler(Looper.getMainLooper()).post {
                                currentPage = 1
                                totalAvailablePage = 1

                                Log.i("tagText",s.toString())
                                adapter.clear()

                                searchTVShow(s.toString())
                            }
                        }
                    }, 800)
                }else{
                    Log.i("tagText","entre en el else")
                    adapter.clear()
                    adapter.notifyDataSetChanged()
                }


            }

        })
        

    }
    fun searchTVShow(query:String){
        searchTVShowViewModel.getSearchTVShow(query,currentPage)
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
        val intent= Intent(this,DetailsTVShowActivity::class.java)
        intent.putExtra("id",tvShow.id.toString())
        intent.putExtra("name",tvShow.name)
        intent.putExtra("startDate",tvShow.starDate)
        intent.putExtra("country",tvShow.country)
        intent.putExtra("network",tvShow.network)
        intent.putExtra("status",tvShow.status)
        //Tambien puedes pasar solo
        //intent.putExtra("tvShow",tvShow)
        startActivity(intent)
    }
}