package com.example.firebase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.firebase.models.Bottle
import kotlinx.android.synthetic.main.bottle_item_editor.*

class BottleItemEditor(var bottle: Bottle): Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.bottle_item_editor, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bottleItemName.setText(bottle.name)
        bottleItemYear.setText(bottle.descripton)
    }

}