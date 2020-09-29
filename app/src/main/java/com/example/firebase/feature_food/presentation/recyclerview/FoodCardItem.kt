package com.example.firebase.feature_food.presentation.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase.feature_food.data.FoodCard
import kotlinx.android.synthetic.main.food_card_item.view.*

class FoodCardItem(itemView: View): RecyclerView.ViewHolder(itemView) {



    fun bind(item: FoodCard){
        itemView.text_name.text = item.type
    }
}