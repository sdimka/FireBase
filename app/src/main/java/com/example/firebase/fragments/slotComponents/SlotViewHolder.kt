package com.example.firebase.fragments.slotComponents

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase.R
import com.example.firebase.models.Slot


class SlotViewHolder(itemView: View, val freeColor: Drawable, val busyColor: Drawable, val selectedColor: Drawable): RecyclerView.ViewHolder(itemView) {

    var tView1 : TextView? = null


    init {
        tView1 = itemView.findViewById(R.id.slotID)
    }

    fun bind(item: Slot, type: Int) {
        tView1?.setText(item.key)
        if (item.store != null) {
            tView1?.background = busyColor
        } else {
            tView1?.background = freeColor
        }
    }

    fun refresh(item: Slot){
        if (item.store != null) {
            tView1?.background = busyColor
        } else {
            tView1?.background = freeColor
        }
    }

}