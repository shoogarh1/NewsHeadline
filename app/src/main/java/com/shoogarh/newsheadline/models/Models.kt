package com.shoogarh.newsheadline.models

import com.google.gson.annotations.SerializedName

data class ArticleList (
    @SerializedName("articles") val articles: List<Article>?,
)

data class Article (
    @SerializedName("author") val author: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("urlToImage") val image: String?
)