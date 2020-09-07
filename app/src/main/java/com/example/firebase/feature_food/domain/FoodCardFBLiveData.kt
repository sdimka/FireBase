package com.example.firebase.feature_food.domain

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.firebase.feature_food.data.FoodCard
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FoodCardFBLiveData: LiveData<List<FoodCard>>(){

    val query = FirebaseDatabase.getInstance()
        .reference
        .child("FoodCard")

    private val LOG_TAG = "FoodCardFBLiveData"
    private val listener = FCValueEventListener()

    override fun onActive() {
        query.addValueEventListener(listener)
    }

    override fun onInactive() {
        query.removeEventListener(listener)
    }

    inner class FCValueEventListener: ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val list = arrayListOf<FoodCard>()
            for (ds in snapshot.children){
                if (ds != null){
                    val fc: FoodCard? = ds.getValue(FoodCard::class.java)
                    fc!!.refId = ds.key
                    list.add(fc)
                }
            }
            value = list
        }

        override fun onCancelled(error: DatabaseError) {
            Log.e(LOG_TAG, "Can't listen to query " + query, error.toException())
        }

    }

}


