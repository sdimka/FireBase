package com.example.firebase.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.firebase.R
import com.example.firebase.models.Slot
import kotlinx.android.synthetic.main.fridge_info.*

class FridgeInfo: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fridge_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fridgeInfoCancel.setOnClickListener {
            val fragment = fragmentManager!!.findFragmentByTag("FridgeInfo");
            if(fragment != null){
            fragmentManager!!.beginTransaction()
                .remove(fragment)
                .commit();}
        }

        fridgeInfoGen.setOnClickListener {
            val id = fridgeInfoID.text.toString()
            val name = fridgeInfoName.text.toString()
            val sizeX = fridgeInfoSizeX.text.toString().toInt()
            val sizeY = fridgeInfoSizeY.text.toString().toInt()

        }
    }

    fun generator(sX: Int,sY: Int) {
        val list = mutableMapOf<String, Slot>()
        for (i in 1..sY){
            for (k in 1..sX){
                val ind: String = k.toString() + "." + i.toString()
                list.put(ind, Slot())
            }
        }
    }
}