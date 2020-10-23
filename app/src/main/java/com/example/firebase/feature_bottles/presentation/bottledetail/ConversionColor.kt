package com.example.firebase.feature_bottles.presentation.bottledetail

import androidx.databinding.InverseMethod
import com.example.firebase.R
import kotlinx.android.synthetic.main.fragment_tab1.view.*


object ConversionColor {
    @InverseMethod("idToInt")
    fun intToID(oldValue: Number?, value: Number?): Int? {
        when(value){
            1 -> return R.id.radio_button_red
            2 -> return R.id.radio_button_white
            3 -> return R.id.radio_button_rose
            else -> return null
        }
    }

    fun idToInt(oldValue: Number?, value: Number?): Number {
        when(value){
            R.id.radio_button_red -> return 1
            R.id.radio_button_white -> return 2
            R.id.radio_button_rose -> return 3
            else -> return 0
        }
    }

}