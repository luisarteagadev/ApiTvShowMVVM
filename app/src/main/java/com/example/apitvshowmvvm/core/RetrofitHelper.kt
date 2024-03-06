package com.example.apitvshowmvvm.core

import com.example.apitvshowmvvm.data.network.ApiServices
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitHelper {

    val client= OkHttpClient.Builder()
        .connectTimeout(20,TimeUnit.SECONDS)
        .readTimeout(20,TimeUnit.SECONDS)
        .build()

    private val baseUrl:String="https://www.episodate.com/api/"
    private  val builder:Retrofit.Builder=Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())

    fun build(): ApiServices {
        return builder.build().create(ApiServices::class.java)
    }
    fun getRetrofit():Retrofit{
        return builder.build()
    }
}