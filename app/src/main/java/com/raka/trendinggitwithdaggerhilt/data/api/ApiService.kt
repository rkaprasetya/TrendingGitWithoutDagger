package com.raka.myapplication.data.api

import com.raka.myapplication.data.model.GitResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search/repositories")
    fun getRepo(@Query("q") search: String = "trending", @Query("sort") sort: String = "stars"): Call<GitResponse>

    @GET("search/repositories")
    suspend fun getRepoRx(@Query("q") search: String = "trending", @Query("sort") sort: String = "stars"): GitResponse
}