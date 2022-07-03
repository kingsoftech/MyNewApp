package com.example.mynewapp.network

import com.example.mynewapp.network.models.TopNewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("Top-headlines")

    fun fetTopArticles(@Query("country") country:String,
    @Query("apiKey") apiKey:String): Call<TopNewsResponse>
}