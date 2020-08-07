package com.example.firebase.services

import android.util.Log
import com.google.gson.GsonBuilder

class JsonExp {

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