package com.example.firebase.feature_bottles.presentation.bottlelist.recyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase.R
import com.example.firebase.feature_bottles.data.model.Bottle
import com.squareup.picasso.Picasso

class BottleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    var tView1 : TextView? = null
    var tView2 : TextView? = null
    var tView3 : TextView? = null
    var iView1 : ImageView? = null

    init {
        tView1 = itemView.findViewById(R.id.tv_1)
        tView2 = itemView.findViewById(R.id.tv_2)
        tView3 = itemView.findViewById(R.id.tv_3)
        iView1 = itemView.findViewById(R.id.iv_1)
    }


    fun bind(item: Bottle){
        tView1?.setText(item.refID.toString())
        tView2?.setText(item.name)
        tView3?.setText(item.year.toString())

        if (item.bottleImage != null) {
            Picasso.get()
                .load(item.bottleImage)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_baseline_error_outline_24)
                .into(iView1)
        } else {
                Picasso.get()
                    .load(R.drawable.ic_no_image1)
                    .placeholder(R.drawable.ic_no_image1)
                    .into(iView1)

        }

    }

}