package com.example.firebase.feature_food.presentation.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase.R
import com.example.firebase.feature_food.data.FoodCard
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.food_card_item.view.*
import kotlinx.android.synthetic.main.fragmet_food_card.*

class FoodCardItem(itemView: View): RecyclerView.ViewHolder(itemView) {



    fun bind(item: FoodCard){
        itemView.text_name.text = item.type

        if (item.pict != null) {
            Picasso.get()
                .load(item.pict)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_baseline_error_outline_24)
                .into(itemView.image_main)
        } else {
            Picasso.get()
                .load(R.drawable.ic_no_image1)
                .placeholder(R.drawable.ic_no_image1)
                .into(itemView.image_main)

        }

        if (item.icon != null) {
            Picasso.get()
                .load(item.icon)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_baseline_error_outline_24)
                .into(itemView.image_icon)
        } else {
            Picasso.get()
                .load(R.drawable.ic_no_image1)
                .placeholder(R.drawable.ic_no_image1)
                .into(itemView.image_icon)

        }
    }
}