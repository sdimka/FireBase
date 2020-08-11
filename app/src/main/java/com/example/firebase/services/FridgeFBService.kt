package com.example.firebase.services

import com.example.firebase.models.Fridge
import com.example.firebase.models.Slot
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
}