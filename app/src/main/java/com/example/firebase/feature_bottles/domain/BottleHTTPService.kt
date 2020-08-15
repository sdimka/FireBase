package com.example.firebase.feature_bottles.domain

import com.example.firebase.BottleJson
import com.example.firebase.FireBaseApp
import com.example.firebase.feature_bottles.data.model.Bottle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BottleHTTPService {

    fun getList(): ArrayList<Bottle>{
//        loader.visibility = View.VISIBLE

        val bottleList = arrayListOf<Bottle>()

        FireBaseApp.instance.bottlesService!!.getBottlesByAId(1)
            .enqueue(object : Callback<List<BottleJson>> {
                override fun onFailure(call: Call<List<BottleJson>>, t: Throwable) {
                    t.printStackTrace()
//                    loader.visibility = View.GONE
//                    Toast.makeText(this@MainActivity,
//                        "ERROR " + t.javaClass.simpleName,
//                        Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<List<BottleJson>>,
                    response: Response<List<BottleJson>>
                ) {
                    if(response.isSuccessful){
//                        bottleList.clear()
                        var bottleJsonList = response.body()
                        bottleJsonList!!.forEach { bottleJson:
                                                   BottleJson -> bottleList.add(
                            Bottle(
                                bottleJson
                            )
                        ) }
//                        recyclerBottle.adapter?.notifyDataSetChanged()

                    } else {
//                        Toast.makeText(this@MainActivity,
//                            "FAIL " + response.code(),
//                            Toast.LENGTH_SHORT).show()
                    }
//                    loader.visibility = View.GONE
                }


            })
        return bottleList
    }
}