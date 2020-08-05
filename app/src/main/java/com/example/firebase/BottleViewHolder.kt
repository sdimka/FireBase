package com.example.firebase

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BottleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var tView1 : TextView? = null
    var tView2 : TextView? = null
    var tView3 : TextView? = null

    init {
        tView1 = itemView.findViewById(R.id.tv_1)
        tView2 = itemView.findViewById(R.id.tv_2)
        tView3 = itemView.findViewById(R.id.tv_3)
    }

    fun bind(item: Bottle){
        tView1?.setText(item.id.toString())
        tView2?.setText(item.name)
        tView3?.setText(item.descripton)
    }
}