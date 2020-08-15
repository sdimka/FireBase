package com.example.firebase.feature_bottles.presentation.bottlelist.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase.R
import com.example.firebase.feature_bottles.data.model.Bottle

class BottleViewAdapter (val list: List<Bottle>): RecyclerView.Adapter<BottleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BottleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BottleViewHolder(
            inflater.inflate(
                R.layout.bottle_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BottleViewHolder, position: Int) {
        val rec : Bottle = list[position]
        holder.bind(rec)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

    }

}