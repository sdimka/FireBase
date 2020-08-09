package com.example.firebase.fragments.slotComponents

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase.R
import com.example.firebase.models.Slot

class SlotViewAdapter (val list: List<Slot>) : RecyclerView.Adapter<SlotViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlotViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SlotViewHolder(inflater.inflate(R.layout.slot_item, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: SlotViewHolder, position: Int) {
        val slot : Slot = list[position]
        holder.bind(slot, 1)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

    }

}