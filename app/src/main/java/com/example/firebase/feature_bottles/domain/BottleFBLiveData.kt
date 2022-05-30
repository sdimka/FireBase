package com.example.firebase.feature_bottles.domain

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.firebase.feature_bottles.data.model.Bottle
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue

class BottleFBLiveData: LiveData<List<Bottle>>() {

    private val query = FirebaseDatabase.getInstance().reference.child("Bottles")
    private val listener = BottlesValueEventListener()

    private val TAG = "BottleFBLiveData"

    override fun onActive() {
        query.addValueEventListener(listener)
    }

    override fun onInactive() {
        query.removeEventListener(listener)
    }

    inner class BottlesValueEventListener: ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val list = arrayListOf<Bottle>()
            for (data in snapshot.children){
                data.getValue<Bottle>()?.let {
                    it.refID = data.key
                    list.add(it)
                }
            }
            value = list
        }

        override fun onCancelled(error: DatabaseError) {
            Log.e(TAG, "Can't listen to query " + query, error.toException())
        }

    }
}