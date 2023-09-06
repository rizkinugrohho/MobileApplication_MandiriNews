package com.mandiri.mandirinewss.api

data class MainNews(
    var status: String,
    var totalResults: String,
    var articles: ArrayList<ModelClass>
)