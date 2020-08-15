package com.example.firebase.feature_bottles.presentation.bottledetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.firebase.R
import com.example.firebase.feature_bottles.data.model.Bottle
import com.example.firebase.feature_bottles.domain.BottleFBService
import kotlinx.android.synthetic.main.bottle_item_editor.*

class BottleItemEditor(
    val bottle: Bottle,
    val key: String?
): Fragment() {

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
        bottleItemFBKey.text = key

        bottleItemSave.setOnClickListener {
            bottle.name = bottleItemName.text.toString()
            BottleFBService.instance.upDate(key!!, bottle)
        }
    }

    fun save(fbKey: String){

    }

}