package com.example.firebase

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FireBaseApp: Application() {
    var bottlesService: BottlesService? = null

    companion object {
        lateinit var instance: FireBaseApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initRetrofit()
    }

//    fun getInst(): FireBaseApp {
//        return instance
//    }

    fun initRetrofit(){
        val baseUrl: String = "https://jsonplaceholder.typicode.com/"
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        bottlesService = retrofit.create(BottlesService::class.java)

    }
}