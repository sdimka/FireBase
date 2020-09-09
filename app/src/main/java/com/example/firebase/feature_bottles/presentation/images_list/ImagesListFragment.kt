package com.example.firebase.feature_bottles.presentation.images_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.firebase.R
import com.example.firebase.feature_bottles.presentation.images_list.recyclerview.ImageListAdapter

class ImagesListFragment: Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frament_images_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

//        val adap = ImageListAdapter()
    }
}