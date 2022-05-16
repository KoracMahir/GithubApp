package com.example.githubapp.features.repository

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubapp.R
import com.example.githubapp.features.search.SearchPresenter
import com.example.githubapp.features.search.SearchView
import com.example.githubapp.models.details.RepositoryDetailsResponse

class RepositoryDetailsFragment : Fragment(), RepositoryDetailsView {

    var repositoryDetailsPresenter = RepositoryDetailsPresenter(this)
    private val loadingBar : ProgressBar? = null
    private var recyclerView : RecyclerView? = null
    private var title: TextView? = null
    private var full_title: TextView? = null
    private var description: TextView? = null
    private var fork_count: TextView? = null
    private var profile_image: ImageView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_repository_details, container, false)

        arguments?.let { args ->
            val userName = args.getString("user_name")
            val repoName = args.getString("name")
            if (userName != null && repoName != null) {
                repositoryDetailsPresenter.getRepositoryDetails(userName,repoName)
            }

        }

        title = view.findViewById(R.id.repository_title) as TextView
        full_title = view.findViewById(R.id.repository_full_title) as TextView
        description = view.findViewById(R.id.description) as TextView
        fork_count = view.findViewById(R.id.fork_count) as TextView
        profile_image = view.findViewById(R.id.owner_profile_image) as ImageView

        return view
    }

    override fun getRepositoryDetails(searchResponse: RepositoryDetailsResponse) {
        title?.text = searchResponse.name
        full_title?.text = searchResponse.full_name
        description?.text = searchResponse.description
        fork_count?.text = searchResponse.forks_count.toString()
        profile_image?.let { imageView ->
            Glide.with(this)
                .load(searchResponse.owner.avatar_url)
                .into(imageView)
        }
    }

    override fun onFailure(error: Throwable) {
        Toast.makeText(context,error.message.toString(), Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        loadingBar?.visibility = View.VISIBLE
        loadingBar?.animation?.start()
        recyclerView?.visibility = View.GONE
    }

    override fun hideLoading() {
        loadingBar?.visibility = View.GONE
        recyclerView?.visibility = View.VISIBLE
    }

}