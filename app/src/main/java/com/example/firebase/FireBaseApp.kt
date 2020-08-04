package com.example.firebase

import android.app.Application
import retrofit2.Retrofit

class FireBaseApp: Application() {


    override fun onCreate() {
        super.onCreate()

        initRetrofit()
    }

    fun initRetrofit(){
        var baseUrl: String = "http://jsonplaceholder.typicode.com/posts/"
        var retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .build()

        val bottlesService = retrofit.create(BottlesService::class.java)

    }
}