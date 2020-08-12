package com.example.firebase.fragments.slotComponents

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase.R
import com.example.firebase.models.Fridge
import com.example.firebase.models.Slot
import com.example.firebase.services.FridgeFBService

class SlotViewAdapter (val fridge: Fridge, val typeOfView: Int, val fridgeRef: String?) : RecyclerView.Adapter<SlotViewHolder>(){

    var bColor: Drawable? = null
    var fColor: Drawable? = null
    var sColor: Drawable? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlotViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var bColor = ContextCompat.getDrawable(parent.context, R.drawable.rounded_dark)
        var fColor = ContextCompat.getDrawable(parent.context, R.drawable.rounded_beige)
        var sColor = ContextCompat.getDrawable(parent.context, R.drawable.rounded_wine)

        if (typeOfView == 1) {
            bColor = ContextCompat.getDrawable(parent.context, R.drawable.rounded_dark)
            fColor = ContextCompat.getDrawable(parent.context, R.drawable.rounded_beige)
            sColor = ContextCompat.getDrawable(parent.context, R.drawable.rounded_dark)
        }

        return SlotViewHolder(inflater.inflate(R.layout.slot_item, parent, false),
            fColor!!, bColor!!, sColor!!)
    }

    override fun getItemCount(): Int {
        return fridge.slots!!.size
    }

    override fun onBindViewHolder(holder: SlotViewHolder, position: Int) {
        val slot : Slot = fridge.slots!![position]
        holder.bind(slot, typeOfView)
        if (typeOfView == 1){
            holder.itemView.setOnClickListener{
                if (slot.store == null){
                    FridgeFBService.instance
                        .updateSlot(fridgeRef!!, position.toString(), "-ME454GnPOI5OhtOyDuT")
                    slot.store = "-ME454GnPOI5OhtOyDuT"
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

        }

    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

    }

}