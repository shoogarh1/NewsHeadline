package com.shoogarh.newsheadline.services

import com.shoogarh.newsheadline.interfaces.RestApi
import com.shoogarh.newsheadline.models.ArticleList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestApiService {
    fun getArticles(onResult: (ArticleList?) -> Unit){
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)

        retrofit.fetchArticles().enqueue(
            object : Callback<ArticleList> {
                override fun onFailure(call: Call<ArticleList>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse( call: Call<ArticleList>, response: Response<ArticleList>) {
                    val articles = response.body()
                    onResult(articles)
                }
            }
        )
    }
}