package com.example.githubapp.features.search

import com.example.githubapp.models.failiure.FailedResponse
import com.example.githubapp.models.search.SearchResponse

interface SearchView {
    fun getSearchResults(searchResponse: SearchResponse)
    fun onFailure(error: Throwable)
    fun showLoading()
    fun hideLoading()
}