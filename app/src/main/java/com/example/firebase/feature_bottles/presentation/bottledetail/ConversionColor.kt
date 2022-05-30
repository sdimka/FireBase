package com.example.firebase.feature_bottles.presentation.bottledetail

import androidx.databinding.InverseMethod
import com.example.firebase.R
import kotlinx.android.synthetic.main.fragment_tab1.view.*


object ConversionColor {
    @InverseMethod("idToInt")
    fun intToID(oldValue: Number?, value: Number?): Int? {
        return when(value){
            1 -> R.id.radio_button_red
            2 -> R.id.radio_button_white
            3 -> R.id.radio_button_rose
            else -> null
        }
    }

    fun idToInt(oldValue: Number?, value: Number?): Number {
        return when(value){
            R.id.radio_button_red -> 1
            R.id.radio_button_white -> 2
            R.id.radio_button_rose -> 3
            else -> 0
        }
    }

    @InverseMethod("idToOrigin")
    fun originToID(oldValue: Number?, value: Number?): Int? {
        return when(value){
            1 -> R.id.radio_button_origin_domestic
            2 -> R.id.radio_button_origin_foreign
            3 -> R.id.radio_button_origin_sparkling
            else -> null
        }
    }

    fun idToOrigin(oldValue: Number?, value: Number?): Number {
        return when(value){
            R.id.radio_button_origin_domestic -> 1
            R.id.radio_button_origin_foreign -> 2
            R.id.radio_button_origin_sparkling -> 3
            else -> 0
        }
    }

}