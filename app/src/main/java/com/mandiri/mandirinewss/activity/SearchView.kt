package com.mandiri.mandirinewss.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mandiri.mandirinewss.adapter.RecycleAdapter
import com.mandiri.mandirinewss.api.ApiUtilities
import com.mandiri.mandirinewss.api.MainNews
import com.mandiri.mandirinewss.api.ModelClass
import com.mandiri.mandirinewss.databinding.ActivitySearchBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchView: AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private val api = "8b034fd9e0194520abef9d8328162d85"

    private lateinit var modelClassArrayList: ArrayList<ModelClass>
    private lateinit var recycleAdapter: RecyclerView.Adapter<*>
    private lateinit var q: String
    private lateinit var recyclerViewofSearch: RecyclerView
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)


        modelClassArrayList = ArrayList()
        recyclerViewofSearch = binding.recycleOfSearch
        recyclerViewofSearch.setLayoutManager(LinearLayoutManager(this))

        recycleAdapter = RecycleAdapter(this, modelClassArrayList)
        recyclerViewofSearch.adapter = recycleAdapter
        searchView = binding.searchBar

        searchView.setOnQueryTextListener( object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    q = query
                    findNews(q)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return false
            }
        })


//        Back Button
        val backBtn = binding.backBtn
        backBtn.setOnClickListener(){
            val intentBack = Intent(this, MainActivity::class.java)
            startActivity(intentBack)
        }
    }


    private fun findNews(q : String) {
        val utilities = ApiUtilities().getApiInterface().getSearchNews(q, 20, api)
        utilities.enqueue(object : Callback<MainNews> {

            override fun onResponse(call: Call<MainNews>, response: Response<MainNews>) {
                Log.d("TAG", "onResponse: Masuk OnResponse")
                if (response.isSuccessful) {

                    for (i in response.body()!!.articles) {
                        if (i.title != null &&
                            i.url != null &&
                            i.urlToImage != null
                        ) {

                            Log.d("TAG", "Filter selesai")
                            modelClassArrayList.add(i)
                        }
                    }
//                    Refresh Adapter
                    recycleAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<MainNews>, t: Throwable) {
                Log.d("TAG", "onFailure: Gagal")
            }

        })
    }
}