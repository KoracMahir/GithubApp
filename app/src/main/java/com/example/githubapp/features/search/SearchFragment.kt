package com.example.githubapp.features.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapp.R
import com.example.githubapp.adapter.SearchAdapter
import com.example.githubapp.models.search.SearchResponse


class SearchFragment : Fragment(), SearchView{

    var searchPresenter = SearchPresenter(this)
    private val loadingBar : ProgressBar? = null
    private var recyclerView : RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        recyclerView = view.findViewById(R.id.search_recycler_view)
        getSearch(view)
        return view;
    }

    fun getSearch(view: View){
        val edittext = view.findViewById<EditText>(R.id.menu_search)
        try {
            edittext.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                    if(p0.toString().isNotEmpty())
                        searchPresenter.getSearchResults(p0.toString())
                }
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            })
        }catch (e:Exception){
            Log.e("SEARCH_ERROR",e.message.toString())
        }
    }

    override fun getSearchResults(searchResponse: SearchResponse) {
        val adapter = SearchAdapter(searchResponse)
        recyclerView?.visibility = View.VISIBLE
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView?.layoutManager = linearLayoutManager
        recyclerView?.adapter = adapter
    }

    override fun onFailure(error: Throwable) {
        Toast.makeText(context,error.message.toString(),Toast.LENGTH_LONG).show()
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