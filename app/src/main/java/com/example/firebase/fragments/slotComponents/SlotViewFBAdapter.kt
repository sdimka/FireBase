package com.example.firebase.fragments.slotComponents

import android.view.ViewGroup
import com.example.firebase.models.Slot
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class SlotViewFBAdapter (opt : FirebaseRecyclerOptions<Slot>) : FirebaseRecyclerAdapter <Slot, SlotViewHolder>(opt){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlotViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: SlotViewHolder, position: Int, model: Slot) {
        TODO("Not yet implemented")
    }

}