package com.example.firebase.feature_food.presentation.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase.R
import com.example.firebase.feature_food.data.FoodCard

class FoodCardListAdapter: RecyclerView.Adapter<FoodCardItem>() {

    val foodCards = arrayListOf<FoodCard>()

    private var clickListener: ((foodCard: FoodCard) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodCardItem {
        val inflater = LayoutInflater.from(parent.context)
        return FoodCardItem(inflater.inflate(R.layout.food_card_item, parent, false))
    }

    override fun onBindViewHolder(holder: FoodCardItem, position: Int) {
        holder.bind(foodCards[position])
        holder.itemView.setOnClickListener { clickListener!!.invoke(foodCards[position])  }
    }

    override fun getItemCount(): Int {
        return foodCards.size
    }

    fun setList(list: List<FoodCard>?) {
        foodCards.clear()
        if (list != null) {
            foodCards.addAll(list)
        }
    }

    fun setClickListener(listener: (foodCard: FoodCard) -> Unit){
        clickListener = listener
    }
}