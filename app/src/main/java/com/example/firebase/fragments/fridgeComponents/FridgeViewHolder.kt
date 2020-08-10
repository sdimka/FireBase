package com.example.firebase.fragments.fridgeComponents

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase.R
import com.example.firebase.models.Fridge
import com.example.firebase.models.Slot

class FridgeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var tViewName: TextView? = null
    var tViewSizeX: TextView? = null
    var tViewSizeY: TextView? = null


    init {
        tViewName = itemView.findViewById(R.id.fridgeItemName)
        tViewSizeX = itemView.findViewById(R.id.fridgeItemSizeX)
        tViewSizeY = itemView.findViewById(R.id.fridgeItemSizeY)


    }

    fun bind(item: Fridge) {
        Log.d("FridgeViewHolder", item.id.toString())
        tViewName?.setText(item.name)
        tViewSizeX?.setText(item.sizeX.toString())
        tViewSizeY?.setText(item.sizeY.toString())
    }
}