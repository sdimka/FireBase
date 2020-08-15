package com.example.firebase.feature_fridge.presentation.slotComponents

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase.R
import com.example.firebase.feature_fridge.data.Fridge
import com.example.firebase.feature_fridge.data.Slot
import com.example.firebase.feature_fridge.domain.FridgeFBService

class SlotViewAdapter (val fridge: Fridge, val fridgeRef: String?, val selectedBottle: String?) : RecyclerView.Adapter<SlotViewHolder>(){

    var bColor: Drawable? = null
    var fColor: Drawable? = null
    var sColor: Drawable? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlotViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var bColor = ContextCompat.getDrawable(parent.context, R.drawable.rounded_dark)
        var fColor = ContextCompat.getDrawable(parent.context, R.drawable.rounded_beige)
        var sColor = ContextCompat.getDrawable(parent.context, R.drawable.rounded_dark)

        if (selectedBottle != null) {
            bColor = ContextCompat.getDrawable(parent.context, R.drawable.rounded_dark)
            fColor = ContextCompat.getDrawable(parent.context, R.drawable.rounded_beige)
            sColor = ContextCompat.getDrawable(parent.context, R.drawable.rounded_wine)
        }

        return SlotViewHolder(inflater.inflate(R.layout.slot_item, parent, false),
            fColor!!, bColor!!, sColor!!)
    }

    override fun getItemCount(): Int {
        return fridge.slots!!.size
    }

    override fun onBindViewHolder(holder: SlotViewHolder, position: Int) {
        val slot : Slot = fridge.slots!![position]
        holder.bind(slot, selectedBottle)
        if (selectedBottle != null){
            holder.itemView.setOnClickListener{
                if (slot.store == null){
                    FridgeFBService.instance
                        .updateSlot(fridgeRef!!, position.toString(), selectedBottle)
                    slot.store = selectedBottle
                    holder.refresh(slot)
                } else {
                    FridgeFBService.instance
                        .updateSlot(fridgeRef!!, position.toString(), null)
                    holder.tView1?.background = fColor
                    slot.store = null
                    holder.refresh(slot)
                }
            }
        } else {
            holder.itemView.setOnClickListener {
                Toast.makeText(holder.itemView.context, slot.store, Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

    }

}