package com.example.githubapp.features.search

import com.example.githubapp.NetworkingService
import com.example.githubapp.models.failiure.FailedResponse
import com.example.githubapp.models.search.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchPresenter (searchView: SearchView){
    val searchView = searchView

    fun getSearchResults(query:String){
        searchView.showLoading()
        NetworkingService.create()
            .getRepositories(query)
            .enqueue(object : Callback<SearchResponse> {
                override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                    response.body()?.let { searchResponse ->  searchView.getSearchResults(searchResponse) }
                    searchView.hideLoading()
                }
                override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                    searchView.onFailure(t)
                    searchView.hideLoading()
                }
            })
    }
}