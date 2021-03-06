package com.example.firebase.feature_bottles.presentation.bottlelist.recyclerview


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.firebase.R
import com.example.firebase.feature_bottles.data.model.Bottle
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class BottleViewFBAdapter(opt : FirebaseRecyclerOptions<Bottle>,
                          val fragment: Fragment,
                          val listener: (Bottle) -> Unit): FirebaseRecyclerAdapter<Bottle, BottleViewHolder>(opt) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BottleViewHolder {
            return BottleViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.bottle_item, parent, false)
            )
    }

    override fun onBindViewHolder(holder: BottleViewHolder, position: Int, model: Bottle) {
        holder.bind(model)
        model.refID = getRef(position).key

        holder.itemView.setOnClickListener{
            listener(model)
//            fragment.fragmentManager!!
//                .beginTransaction()
//                .replace(R.id.bottleEditorFrameContainer, BottleItemEditor(model, getRef(position).key), "BottleItemEditor")
//                .commit()

        }
    }


}