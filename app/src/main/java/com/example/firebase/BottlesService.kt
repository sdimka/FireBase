package com.example.firebase

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface BottlesService {
    @GET("posts")
    fun getBottles(): Call<List<BottleJson>>

    @GET("photos/(id)")
    fun getBottleById(@Path("id") bottleId: Int): Call<BottleJson>

    @GET("photos?")
    fun getBottlesByAId(@Query("albumId") bottleId: Int): Call<List<BottleJson>>

    @GET("posts?")
    fun getBottlesSorted(@Query("sortBy") sortBy: String) : Call<List<BottleJson>>
//    fun getBottles(@Header("X-Auth-Token") token: String): Call<List<BottleJson>>



}