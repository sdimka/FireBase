package com.example.firebase.services


import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase.FireBaseApp
import com.example.firebase.MainActivity
import com.example.firebase.models.Bottle
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class BottleFBService {

    val databaseReference = Firebase.database.reference
    val bottlesRef = databaseReference.child("Bottles")

    val TAG = "BottleFBService"

    companion object {
        var instance = BottleFBService()
    }




    fun basicReadWrite(arrayList: ArrayList<Bottle>, recyclerView: RecyclerView) {
        // [START write_message]
        // Write a message to the database
//        val database = Firebase.database
//        val myRef = database.getReference("Bottles")

//        myRef.setValue(bottleList)

//        myRef.setValue("Hello, World!")



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
                    Log.d("TAG", bottle?.name.toString())
                    recyclerView.adapter?.notifyDataSetChanged()
                }
            }
        })

        val query = bottlesRef.orderByChild("id").equalTo(1.0)
        query.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("TAG", snapshot.childrenCount.toString())
                for (child in snapshot.children){
                    Log.d("TAG", child.value.toString())
                }
            }
        })
    }

}