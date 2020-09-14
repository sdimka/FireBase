package com.example.firebase.feature_bottles.presentation.bottlelist.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase.R
import com.example.firebase.feature_bottles.data.model.Bottle

class BottleViewAdapter (): RecyclerView.Adapter<BottleViewHolder>() {

    private var bottleList = listOf<Bottle>()
    private var clickListener: ((bottle: Bottle) -> Unit)? = null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BottleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BottleViewHolder(inflater.inflate(R.layout.bottle_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return bottleList.size
    }

    override fun onBindViewHolder(holder: BottleViewHolder, position: Int) {
        val rec : Bottle = bottleList[position]
        holder.bind(rec)
        holder.itemView.setOnClickListener{ clickListener!!.invoke(rec)}
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

    }

    fun setList(list: List<Bottle>){
        bottleList = list
        notifyDataSetChanged()
    }

    fun setHolderClickListener(listener: (bottle: Bottle) -> Unit){
        clickListener = listener
    }

}