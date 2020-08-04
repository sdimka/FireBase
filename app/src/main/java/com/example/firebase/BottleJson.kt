package com.example.firebase

import com.google.gson.annotations.SerializedName

class BottleJson (
    val id: Int,
    @SerializedName("title") val name: String,
    @SerializedName("body")
    val description: String
)