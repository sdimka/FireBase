package com.example.firebase.fragments.slotComponents

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase.R
import com.example.firebase.models.Slot


class SlotViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    var tView1 : TextView? = null


    init {
        tView1 = itemView.findViewById(R.id.slotID)

    }

    fun bind(item : Slot, type : Int){
        tView1?.setText(item.key)

//        when(type){
//            1 -> tView1?.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.lightBege))
//            2 -> tView1?.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.lightBlack))
//            3 -> tView1?.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.lightWine))
//
//        }
    }
}