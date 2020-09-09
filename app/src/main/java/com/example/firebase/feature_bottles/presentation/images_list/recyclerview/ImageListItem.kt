package com.example.firebase.feature_bottles.presentation.images_list.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.image_item.view.*

class ImageListItem(itemView: View): RecyclerView.ViewHolder(itemView) {


    fun bind(item: String){
        itemView.image_item_image_name.text = item
    }

}