package com.shoogarh.newsheadline.interfaces

import com.shoogarh.newsheadline.models.ArticleList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface RestApi {


    @Headers("Content-Type: application/json")
    @GET("/v2/top-headlines?country=us&apiKey=2d021085c2e64c23927ff485d9f4299b")
    fun fetchArticles(): Call<ArticleList>
}