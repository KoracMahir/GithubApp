package com.example.githubapp.networking

import com.example.githubapp.models.details.RepositoryDetailsResponse
import com.example.githubapp.models.search.SearchResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkingService {

    @GET("search/repositories?")
    fun getRepositories(@Query("q") query:String) : Call<SearchResponse>

    @GET("repos/{user_name}/{repo_name}")
    fun getRepositoryDetails(@Path("user_name") user_name:String, @Path("repo_name") repo_name:String) : Call<RepositoryDetailsResponse>

    companion object {
        fun create(): NetworkingService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.github.com")
                .build()
            return retrofit.create(NetworkingService::class.java)
        }
    }
}