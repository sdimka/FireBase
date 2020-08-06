package com.example.firebase

import com.google.gson.annotations.SerializedName

class BottleJson (
    val id: Int,
    @SerializedName("title") val name: String,
    @SerializedName("url")
    val description: String,
    @SerializedName("thumbnailUrl")
    val img: String
)