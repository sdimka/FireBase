package com.example.firebase.services

import android.util.Log
import android.widget.TextView
import com.example.firebase.models.Fridge
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FridgeFBService {
    val databaseReference = Firebase.database.reference
    val fridgeRef = databaseReference.child("Fridge")

    val TAG = "FridgeFBService"

    companion object {
        var instance = FridgeFBService()
    }

    fun addFridge(fridge: Fridge){
        fridgeRef.push().setValue(fridge)
    }

    fun updateSlot(fridgeKey: String, slotID: String, bottleID : String?){
        fridgeRef.child(fridgeKey).child("slots").child(slotID).child("store").setValue(bottleID)
    }

    fun getBusySlotCount(fridgeKey: String?, view: TextView){
        Log.d(TAG, fridgeKey.toString())

        val query = fridgeRef.child(fridgeKey!!).child("slots")
        query.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                var count = 0
                for (slot in snapshot.children) {
                    val store = slot.child("store").value
                    if (store != null) count++
                    Log.d(TAG, count.toString())
                }
                view.setText(count.toString())
            }

        })
    }

    fun getBusyBottleSlotCount(fridgeKey: String, bootleKey: String): Int {
        return 42
    }
}