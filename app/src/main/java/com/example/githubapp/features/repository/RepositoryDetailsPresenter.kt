package com.example.githubapp.features.repository

import com.example.githubapp.networking.NetworkingService
import com.example.githubapp.models.details.RepositoryDetailsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryDetailsPresenter (repositoryDetailsView: RepositoryDetailsView){
    val repositoryDetailsView = repositoryDetailsView

    fun getRepositoryDetails(userName:String, repoName:String){
        repositoryDetailsView.showLoading()
        NetworkingService.create()
            .getRepositoryDetails(userName, repoName)
            .enqueue(object : Callback<RepositoryDetailsResponse> {
                override fun onResponse(call: Call<RepositoryDetailsResponse>, response: Response<RepositoryDetailsResponse>) {
                    response.body()?.let { searchResponse ->  repositoryDetailsView.getRepositoryDetails(searchResponse) }
                    repositoryDetailsView.hideLoading()
                }
                override fun onFailure(call: Call<RepositoryDetailsResponse>, t: Throwable) {
                    repositoryDetailsView.onFailure(t)
                    repositoryDetailsView.hideLoading()
                }
            })
    }
}