package com.example.githubapp

import com.example.githubapp.models.search.SearchResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkingService {

    @GET("search/repositories?")
    fun getRepositories(@Query("q") query:String) : Call<SearchResponse>

    companion object {
        fun create(): NetworkingService{
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.github.com")
                .build()
            return retrofit.create(NetworkingService::class.java)
        }
    }
}