package com.example.firebase.feature_bottles.presentation.images_list.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase.R
import com.example.firebase.feature_bottles.data.model.ImageInfo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.image_item.view.*

class ImageListItem(itemView: View): RecyclerView.ViewHolder(itemView) {


    fun bind(item: ImageInfo){
        itemView.image_item_image_name.text = item.name
        itemView.image_item_uri.text = item.link
        Picasso.get()
            .load(item.uri)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_baseline_error_outline_24)
            .into(itemView.image_item_image)
    }

}