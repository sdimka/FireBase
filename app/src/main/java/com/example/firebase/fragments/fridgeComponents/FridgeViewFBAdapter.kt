package com.example.firebase.fragments.fridgeComponents

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.firebase.R
import com.example.firebase.fragments.SlotsFragment
import com.example.firebase.models.Fridge
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference

class FridgeViewFBAdapter(opt : FirebaseRecyclerOptions<Fridge>, val fragment: Fragment) : FirebaseRecyclerAdapter<Fridge, FridgeViewHolder>(opt){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FridgeViewHolder {
        return FridgeViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fridge_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FridgeViewHolder, position: Int, model: Fridge) {
        holder.bind(model)
        holder.itemView.setOnClickListener{
            fragment.fragmentManager!!
                .beginTransaction()
                .addToBackStack("SlotsFragment")
                .replace(R.id.fridgeEditorSlots, SlotsFragment(model!!, getRef(position).key ), "SlotsFragment" )
                .commit()
        }
    }

}