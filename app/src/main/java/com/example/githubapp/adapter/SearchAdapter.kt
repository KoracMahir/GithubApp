package com.example.githubapp.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubapp.R
import com.example.githubapp.models.search.SearchResponse

class SearchAdapter(private val response: SearchResponse) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    // holder class to hold reference
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //get view reference
        var title: TextView = view.findViewById(R.id.repository_title) as TextView
        var full_title: TextView = view.findViewById(R.id.repository_full_title) as TextView
        var description: TextView = view.findViewById(R.id.description) as TextView
        var fork_count: TextView = view.findViewById(R.id.fork_count) as TextView
        var profile_image: ImageView = view.findViewById(R.id.owner_profile_image) as ImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // create view holder to hold reference
        return ViewHolder( LayoutInflater.from(parent.context).inflate(R.layout.search_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //set values
        holder.title.text = response.items[position].name
        holder.full_title.text = response.items[position].full_name
        holder.description.text = response.items[position].description
        holder.fork_count.text = response.items[position].forks_count.toString()
        Glide.with(holder.itemView)
            .load(response.items[position].owner.avatar_url)
            .into(holder.profile_image)
        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("user_name", response.items[position].owner.login)
            bundle.putString("name", response.items[position].name)
//            Navigation.createNavigateOnClickListener(R.id.action_searchFragment_to_repositoryDetailsFragment,bundle)
            navController(holder.itemView).navigate(R.id.action_searchFragment_to_repositoryDetailsFragment,bundle)
        }
    }

    override fun getItemCount(): Int {
        return response.items.size
    }

    fun navController(view: View): NavController {
        val navController = Navigation.findNavController(view)
        return navController
    }
}