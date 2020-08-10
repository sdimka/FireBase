package com.example.firebase.fragments.fridgeComponents

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.firebase.R
import com.example.firebase.models.Fridge
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class FridgeViewFBAdapter(opt : FirebaseRecyclerOptions<Fridge>) : FirebaseRecyclerAdapter<Fridge, FridgeViewHolder>(opt){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FridgeViewHolder {
        return FridgeViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fridge_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FridgeViewHolder, position: Int, model: Fridge) {
        holder.bind(model)
    }

}