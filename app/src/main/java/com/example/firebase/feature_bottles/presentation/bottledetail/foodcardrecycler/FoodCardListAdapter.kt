package com.example.firebase.feature_bottles.presentation.bottledetail.foodcardrecycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase.R
import com.example.firebase.feature_bottles.data.model.Bottle
import com.example.firebase.feature_food.data.FoodCard
import kotlinx.android.synthetic.main.food_card_small.view.*

class FoodCardListAdapter: RecyclerView.Adapter<FoodCardItem>() {

    private var cardList = listOf<FoodCard>()
    private lateinit var currentBottle: Bottle

    private var clickListener: ((foodCard: FoodCard) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodCardItem {
        val inflater = LayoutInflater.from(parent.context)
        return FoodCardItem(inflater.inflate(R.layout.food_card_small, parent, false))
    }

    override fun onBindViewHolder(holder: FoodCardItem, position: Int) {
        holder.bind(cardList[position])
        holder.itemView.setOnClickListener { clickListener!!.invoke(cardList[position])  }
        if (::currentBottle.isInitialized && currentBottle.foodCombines != null &&
            (cardList[position].refId?.let { currentBottle.foodCombines?.contains(it) }!!)){

            holder.itemView.checked_icon.visibility = View.VISIBLE
        } else holder.itemView.checked_icon.visibility = View.INVISIBLE
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    fun setClickListener(listener: (foodCard: FoodCard) -> Unit) {
        clickListener = listener
    }

    fun setList(list: List<FoodCard>?) {
        if (list != null) {
            cardList = list
        }
    }

    fun setCurrentBottle(bottle: Bottle?) {
        if (bottle != null) {
            currentBottle = bottle
        }
    }

}