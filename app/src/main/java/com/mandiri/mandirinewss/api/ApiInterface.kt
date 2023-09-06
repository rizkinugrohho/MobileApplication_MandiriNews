package com.mandiri.mandirinewss.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    companion object {
        const val BASE_URL = "https://newsapi.org/v2/"
    }

    @GET("top-headlines")
    fun getHeadlines(
        @Query("apikey") apikey: String
    ): Call<MainNews>

    @GET("everything")
    fun getCategory(
        @Query("q") query: String,
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int,
        @Query("apikey") apikey: String
    ): Call<MainNews>

    @GET("everything")
    fun getSearchNews(
        @Query("q") q : String,
        @Query("pageSize") pageSize: Int,
        @Query("apikey") apikey: String
    ): Call<MainNews>

}