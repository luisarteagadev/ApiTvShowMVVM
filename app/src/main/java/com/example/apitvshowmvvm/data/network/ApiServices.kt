package com.example.apitvshowmvvm.data.network

import android.util.Log
import com.example.apitvshowmvvm.core.RetrofitHelper
import com.example.apitvshowmvvm.data.models.TVShowDetailsModel
import com.example.apitvshowmvvm.data.responses.TVShowDetailResponse
import com.example.apitvshowmvvm.data.responses.TVShowsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query
import java.net.SocketTimeoutException

class ApiServices {

    private val retrofit=RetrofitHelper.getRetrofit()




    suspend fun getMostPopularTVShows(page:Int): TVShowsResponse {
        return withContext(Dispatchers.IO){
            try {
                val response = retrofit.create(ApiClient::class.java).getMostPopularTVShows(page)
                response.body() ?: TVShowsResponse(0, 0, emptyList())
            } catch (e: SocketTimeoutException) {
                // Manejar la excepción de tiempo de espera
                Log.i("tagNetwork","Se agotó el tiempo de espera: ${e.message}")
                // Puedes lanzar una excepción personalizada, mostrar un mensaje, o realizar otra acción aquí
                TVShowsResponse(0, 0, emptyList())
            } catch (e: HttpException) {
                // Manejar otras excepciones de Retrofit, como códigos de respuesta HTTP no exitosos
                Log.i("tagNetwork","Error de red: ${e.message}")
                // Puedes lanzar una excepción personalizada, mostrar un mensaje, o realizar otra acción aquí
                TVShowsResponse(0, 0, emptyList())
            } catch (e: Exception) {
                // Manejar otras excepciones generales
                Log.i("tagNetwork","Ocurrió una excepción: ${e.message}")
                // Puedes lanzar una excepción personalizada, mostrar un mensaje, o realizar otra acción aquí
                TVShowsResponse(0, 0, emptyList())
            }
        }
    }

    suspend fun getTVShowDetails(idTVShow:String):TVShowDetailResponse?{
        return withContext(Dispatchers.IO){
            val response=retrofit.create(ApiClient::class.java).getTVShowDetails(idTVShow)
            response.body()?: null
        }
    }

    suspend fun getSearchTVShow(name:String,page: Int):TVShowsResponse?{
        return withContext(Dispatchers.IO){
            val response=retrofit.create(ApiClient::class.java).getSearchTVShow(name,page)
            response.body()?: null
        }
    }

    }
