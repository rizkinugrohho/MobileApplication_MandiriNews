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
import com.mandiri.mandirinewss.databinding.FragmentBusinessBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BusinessFragment : Fragment() {

    private lateinit var _binding: FragmentBusinessBinding
    private val api = "8b034fd9e0194520abef9d8328162d85"
    private var isLoading = false

    private lateinit var modelClassArrayList: ArrayList<ModelClass>
    private lateinit var recycleAdapter: RecyclerView.Adapter<*>
    private val q = "business"
    private lateinit var recyclerViewofBusiness: RecyclerView
    private var pg = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBusinessBinding.inflate(inflater)
        modelClassArrayList = ArrayList()
        recyclerViewofBusiness = _binding.recycleOfBusiness
        recyclerViewofBusiness.setLayoutManager(LinearLayoutManager(context))
        recycleAdapter = RecycleAdapter(context, modelClassArrayList)
        recyclerViewofBusiness.adapter = recycleAdapter

        findNews()

        recyclerViewofBusiness.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, state: Int) {
                if (state == RecyclerView.SCROLL_STATE_IDLE && !isLoading) {
                    loadMoreData()
                }
            }
        })

        return _binding.businessFragment
    }

    private fun findNews(){
        val utilities = ApiUtilities().getApiInterface().getCategory( q,30,pg++,api)
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
        findNews()
        isLoading = false
    }
}
