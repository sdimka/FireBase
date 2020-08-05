package com.example.firebase

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
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


        loader.visibility = View.VISIBLE
        FireBaseApp.instance.bottlesService!!.getBottles()
            .enqueue(object : Callback<List<BottleJson>> {
                override fun onFailure(call: Call<List<BottleJson>>, t: Throwable) {
                    t.printStackTrace()
                    loader.visibility = View.GONE
                    Toast.makeText(this@MainActivity,
                        "ERROR " + t.javaClass.simpleName,
                        Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<List<BottleJson>>,
                    response: Response<List<BottleJson>>
                ) {
                    if(response.isSuccessful){

                        var bottleJsonList = response.body()
                        bottleJsonList!!.forEach { bottleJson:
                                                   BottleJson -> bottleList.add(Bottle(bottleJson)) }
                        recyclerBottle.adapter?.notifyDataSetChanged()

                    } else {
                        Toast.makeText(this@MainActivity,
                            "FAIL " + response.code(),
                            Toast.LENGTH_SHORT).show()
                    }
                    loader.visibility = View.GONE
                }


            })



        val suppFM = supportFragmentManager
        recyclerBottle.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = BottleViewAdapter(bottleList)
        }

//        gsonSamples()

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

        // Print botles as Json
//        var bottleList = BottleRepo.items
//        val bjString = gson.toJson(bottleList)
//        Log.d("gson", bjString)

    }

    inner class SimpleObject(var name: String, var age: Int, var nicknames: List<String>) {
        override fun toString(): String {
            return "SimpleObject(name='$name', age=$age, nicknames=$nicknames)"
        }
    }
}
