package com.example.firebase.feature_food.domain

import com.example.firebase.feature_food.data.FoodCard
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FoodCardFBService {
    val databaseReference = Firebase.database.reference
    val foodCardRef = databaseReference.child("FoodCard")

    val TAG = "FoodCardFBService"

    companion object {
        var instance =
            FoodCardFBService()
    }

    fun upDate(fbKey: String, foodCard: FoodCard) {
        foodCardRef.child(fbKey).setValue(foodCard)
    }

    fun add(foodCard: FoodCard) {
        foodCardRef.push().setValue(foodCard)
    }

    fun delete(fbKey: String) {
        foodCardRef.child(fbKey).removeValue()
    }
}