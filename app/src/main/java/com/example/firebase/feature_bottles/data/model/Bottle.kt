package com.example.firebase.feature_bottles.data.model

import com.example.firebase.BottleJson
import com.example.firebase.R

//class Bottle( var id: String? = null,
//    var name: String? = null, var descripton: String? = null, var img: String? = null)

class Bottle(var refID: String? = null,
             var name: String? = null, var year: Int? = null,
             var alko: Number? = null, var color: Int? = null,
             var sparkling: Boolean? = null, var blend: Boolean? = null,
             var smallImg: Int? = null, var bigImg: Int? = null,
             var bottleImage: String? = null, var bottleCard: Int? = null,
             var backImage: Int? = null,
             var foodCombines: List<Int>? = null) {




// Null default values create a no-argument default constructor, which is needed
// for deserialization from a DataSnapshot.


//             val alko: Number, val color: Int,
//             val sparkling: Boolean, val blend: Boolean,
//             val smallImg: Int, val bigImg: Int,
//             val foodCombines: List<Int> )

//                 init {
//                     img = "SomeURL"
//                 }

    constructor(bottleJson: BottleJson?):
//            this(bottleJson!!.id, bottleJson.name, bottleJson.description, bottleJson.img)
    this("-ME454GXYBOwGVo66qUX",
    "Рислинг",
    2019,
    13.5,
    1,
    sparkling = false,
    blend = false,
    smallImg = R.drawable.bottle,
    bigImg = R.drawable.bottle,
    bottleImage = "R.drawable.bottle",
    bottleCard = R.drawable.bottle,
    backImage = R.drawable.bottle,
    foodCombines = listOf(4, 2, 3))
    {
    }
}