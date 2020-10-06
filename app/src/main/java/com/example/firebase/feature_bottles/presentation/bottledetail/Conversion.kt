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

    @InverseMethod("stringToFloat")
    fun floatToString(oldValue: Number?, value: Number?): String? {
        return if (value == 0) {
            null
        } else value.toString()
    }

    fun stringToFloat(oldValue: Number?, value: String?): Number {
        return if (value == null || value.isEmpty()) {
            0F
        } else try {
            value.toFloat()
        } catch (e: NumberFormatException) {
            oldValue?.let { return oldValue }
            return 0F
        }
    }
}