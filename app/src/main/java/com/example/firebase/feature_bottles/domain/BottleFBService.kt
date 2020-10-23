package com.example.firebase.feature_bottles.domain


import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase.feature_bottles.data.model.Bottle
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class BottleFBService {

    val databaseReference: DatabaseReference = Firebase.database.reference
    val bottlesRef = databaseReference.child("Bottles")

    val TAG = "BottleFBService"

    companion object {
        var instance =
            BottleFBService()
    }

    fun basicReadWrite(arrayList: ArrayList<Bottle>, recyclerView: RecyclerView) {

//        bottleList.forEach{ bottle -> bottlesRef.push().setValue(bottle)}

        arrayList.clear()
        bottlesRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                for (ds in snapshot.children) {
                    val bottle = ds.getValue(Bottle::class.java)
                    arrayList.add(bottle!!)
                    recyclerView.adapter?.notifyDataSetChanged()
                }
            }
        })

        val query = bottlesRef.orderByChild("id").equalTo(1.0)
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                for (child in snapshot.children) {
                    Log.d("TAG", child.value.toString())
                }
            }
        })
    }

    fun upDate(fbKey: String, bottle: Bottle) {
        bottlesRef.child(fbKey).setValue(bottle)
    }

    fun add(bottle: Bottle) {
        bottlesRef.push().setValue(bottle)
    }

    fun delete(fbKey: String) {
        bottlesRef.child(fbKey).removeValue()
    }

    fun getBottleById(fbKey: String): Bottle? {
        val query = bottlesRef.child(fbKey)
        var bottle: Bottle? = null
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                bottle = snapshot.getValue(Bottle::class.java)

            }
        })
        return bottle
    }

}