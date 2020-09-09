package com.example.firebase.feature_bottles.presentation.images_list.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase.R

class ImageListAdapter(val list: List<String>): RecyclerView.Adapter<ImageListItem>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageListItem {
        val inflater = LayoutInflater.from(parent.context)
        return ImageListItem(inflater.inflate(R.layout.image_item, parent, false))
    }

    override fun onBindViewHolder(holder: ImageListItem, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

}