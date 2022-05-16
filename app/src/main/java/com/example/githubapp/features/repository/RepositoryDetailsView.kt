package com.example.githubapp.features.repository

import com.example.githubapp.models.details.RepositoryDetailsResponse
import com.example.githubapp.models.search.SearchResponse

interface RepositoryDetailsView {
    fun getRepositoryDetails(searchResponse: RepositoryDetailsResponse)
    fun onFailure(error: Throwable)
    fun showLoading()
    fun hideLoading()
}