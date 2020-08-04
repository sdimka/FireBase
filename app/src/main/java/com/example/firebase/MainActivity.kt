package com.example.firebase

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
//    val a = R.string.APP_KEY_2
    val b = BuildConfig.FOO

    var bottleList = BottleRepo.items

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        FireBaseApp.instance.bottlesService!!.getBottles()
            .enqueue(object : Callback<BottleJson> {
                override fun onFailure(call: Call<BottleJson>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<BottleJson>, response: Response<BottleJson>) {
                    if(response.isSuccessful){
                        var bottleJsonList = response.body()
                        bottleList.add(Bottle(bottleJsonList))
                    } else {

                    }
                }


            })



        val suppFM = supportFragmentManager
        recyclerBottle.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = BottleViewAdapter(bottleList)
        }

        gsonSamples()

    }

    fun gsonSamples(){
//        val gson: Gson = Gson()
//        val so = SimpleObject("FirstName", 25, listOf("SecondName", "ThirdName"))
//        val jString = gson.toJson(so)
//        Log.d("gson", jString )
//        val  newSO: SimpleObject = gson.fromJson(jString, SimpleObject::class.java)
//        Log.d("gson", newSO.toString())

        val gson = GsonBuilder()
            .serializeNulls()
//            .registerTypeAdapter()
            .create()
        val so = SimpleObject("FirstName", 25, listOf("SecondName", "ThirdName"))
        val jString = gson.toJson(so)
        Log.d("gson", jString )
        val  newSO: SimpleObject = gson.fromJson(jString, SimpleObject::class.java)
        Log.d("gson", newSO.toString())

        var bottleList = BottleRepo.items
        val bjString = gson.toJson(bottleList)
        Log.d("gson", bjString)

    }

    inner class SimpleObject(var name: String, var age: Int, var nicknames: List<String>) {
        override fun toString(): String {
            return "SimpleObject(name='$name', age=$age, nicknames=$nicknames)"
        }
    }
}
