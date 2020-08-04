package com.example.firebase

import retrofit2.Call
import retrofit2.http.GET

interface BottlesService {
    @GET("3")
    fun getBottles(): Call<BottleJson>
}