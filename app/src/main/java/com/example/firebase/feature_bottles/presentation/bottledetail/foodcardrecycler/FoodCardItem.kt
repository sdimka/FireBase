package com.example.firebase.feature_bottles.presentation.bottledetail.foodcardrecycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase.R
import com.example.firebase.feature_food.data.FoodCard
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.food_card_small.view.*

class FoodCardItem(itemView: View): RecyclerView.ViewHolder(itemView) {


    fun bind(foodCard: FoodCard) {

        if (foodCard.icon != null) {
            Picasso.get()
                .load(foodCard.icon)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_baseline_error_outline_24)
                .into(itemView.fc_icon)
        } else {
            Picasso.get()
                .load(R.drawable.ic_no_image1)
                .placeholder(R.drawable.ic_no_image1)
                .into(itemView.fc_icon)
        }

        itemView.fc_name.text = foodCard.type
    }


}