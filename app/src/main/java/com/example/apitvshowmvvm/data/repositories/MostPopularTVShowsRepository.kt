package com.example.apitvshowmvvm.data.repositories
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.apitvshowmvvm.data.network.ApiClient
import com.example.apitvshowmvvm.data.network.ApiServices
import com.example.apitvshowmvvm.data.responses.TVShowsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class MostPopularTVShowsRepository {
    private val api= ApiServices()

    suspend fun getAllTvShows(page:Int): TVShowsResponse {
        val response=api.getMostPopularTVShows(page)
        return response
    }
/*
    fun getMostPopularTVShows(page:Int, callback: (TVShowsResponse?) -> Unit ){
        val request=ApiClient.build().getMostPopularTVShows(page)
        request.enqueue(object :Callback<TVShowsResponse>{
            override fun onResponse(
                call: Call<TVShowsResponse>,
                response: Response<TVShowsResponse>
            ) {
                val tvShowResponse=response.body()
                callback(tvShowResponse)
            }

            override fun onFailure(call: Call<TVShowsResponse>, t: Throwable) {
                Log.i("TAGNAME", "onFailure")
                println(t.message)
                callback(null)
            }


        })

    }
*/
}
