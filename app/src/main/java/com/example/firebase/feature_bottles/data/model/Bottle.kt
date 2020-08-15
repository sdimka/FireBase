package com.example.firebase.feature_bottles.data.model

import com.example.firebase.BottleJson

class Bottle( var id: String? = null,
    var name: String? = null, var descripton: String? = null, var img: String? = null)
// Null default values create a no-argument default constructor, which is needed
// for deserialization from a DataSnapshot.


//             val alko: Number, val color: Int,
//             val sparkling: Boolean, val blend: Boolean,
//             val smallImg: Int, val bigImg: Int,
//             val foodCombines: List<Int> )
             {
//                 init {
//                     img = "SomeURL"
//                 }

    constructor(bottleJson: BottleJson?):
            this(bottleJson!!.id, bottleJson.name, bottleJson.description, bottleJson.img) {
    }
}