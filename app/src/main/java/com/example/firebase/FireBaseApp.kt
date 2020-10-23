package com.example.firebase

import android.app.Application
import com.google.firebase.FirebaseApp
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
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
//        FirebaseApp.initializeApp()
    }

    fun initRetrofit(){
        val baseUrl: String = "https://jsonplaceholder.typicode.com/"

        val httpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        var okHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()

                // Request customization: add request headers
                val requestBuilder = original.newBuilder()
                    .header("Authorization", "MY_API_KEY") // <-- this is the important line

                val request = requestBuilder.build()
                chain.proceed(request)

            }
//            .addInterceptor(httpLoggingInterceptor)   //
            .build()

        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        bottlesService = retrofit.create(BottlesService::class.java)

    }
}