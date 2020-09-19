package com.omersakalli.appcentnews.data.api

import com.omersakalli.appcentnews.data.model.Articles
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("v2/everything?pageSize=10")
    suspend fun getArticlesList(
        @Query("page")
        page:Int,
        @Query("q")
        searchParameter:String,
        @Query("apiKey")
        apiKey:String
    ):Response<Articles>
}