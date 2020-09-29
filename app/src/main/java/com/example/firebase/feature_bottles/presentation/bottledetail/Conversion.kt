package com.example.firebase.feature_bottles.presentation.bottledetail

import androidx.databinding.InverseMethod


object Conversion {
    @InverseMethod("stringToInt")
    fun intToString(oldValue: Number?, value: Number?): String? {
        return if (value == 0) {
            null
        } else value.toString()
    }

    fun stringToInt(oldValue: Number?, value: String?): Number {
        return if (value == null || value.isEmpty()) {
            0
        } else try {
            value.toInt()
        } catch (e: NumberFormatException) {
            oldValue?.let { return oldValue }
            return 0
        }
    }
}