package com.mandiri.mandirinewss.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mandiri.mandirinewss.adapter.RecycleAdapter
import com.mandiri.mandirinewss.api.ApiUtilities
import com.mandiri.mandirinewss.api.MainNews
import com.mandiri.mandirinewss.api.ModelClass
import com.mandiri.mandirinewss.databinding.FragmentEntertainmentBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EntertainmentFragment : Fragment() {

    private lateinit var _binding: FragmentEntertainmentBinding
    private val api = "8b034fd9e0194520abef9d8328162d85"
    private var isLoading = false

    private lateinit var modelClassArrayList: ArrayList<ModelClass>
    private lateinit var recycleAdapter: RecyclerView.Adapter<*>
    private lateinit var recyclerViewofEntertainment: RecyclerView
    private val q = "entertainment"
    private var pg = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEntertainmentBinding.inflate(inflater)
        modelClassArrayList = ArrayList()
        recyclerViewofEntertainment = _binding.recycleOfEntertainment
        recyclerViewofEntertainment.setLayoutManager(LinearLayoutManager(context))
        recycleAdapter = RecycleAdapter(context, modelClassArrayList)
        recyclerViewofEntertainment.adapter = recycleAdapter

        findNews()

        recyclerViewofEntertainment.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, state: Int) {
                if (state == RecyclerView.SCROLL_STATE_IDLE && !isLoading) {
                    loadMoreData()
                }
            }
        })

        return _binding.entertainmentFragment
    }

    private fun findNews(peg: Int = 0){
        val utilities = ApiUtilities().getApiInterface().getCategory(q,30,pg++,api)
        utilities.enqueue(object : Callback<MainNews> {

            override fun onResponse(call: Call<MainNews>, response: Response<MainNews>) {
                Log.d("TAG", "onResponse: Masuk OnResponse")
                if (response.isSuccessful){

                    for (i in response.body()!!.articles){
                        if (i.title != null &&
                            i.url != null &&
                            i.urlToImage != null){

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

    private fun loadMoreData(){
        isLoading = true
        findNews(1)
        isLoading = false
    }
}